<#
setup_ports_and_firewall.ps1
- Idempotent add/remove of portproxy and firewall rules for ports 8080 and 3000
- Usage: run elevated. Use -Remove to delete. Optionally pass -WslDistro "Ubuntu"
#>

param(
    [switch]$Remove,
    [string]$ListenAddress = "0.0.0.0",
    [string]$WslDistro = ""
)

function Assert-IsAdmin {
    $isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
    if (-not $isAdmin) {
        Write-Error "Administrator privileges required. Rerun in an elevated session."
        exit 1
    }
}

function Get-FirstWslIPv4_Hostname {
    try {
        if ($WslDistro) {
            $out = & wsl -d $WslDistro -- bash -lc "hostname -I 2>/dev/null" 2>$null
        } else {
            $out = & wsl -- bash -lc "hostname -I 2>/dev/null" 2>$null
        }
    } catch {
        return $null
    }
    if (-not $out) { return $null }
    $joined = ($out -join ' ').Trim()
    if (-not $joined) { return $null }
    $m = [regex]::Matches($joined, '\b(?:(?:25[0-5]|2[0-4]\d|1?\d{1,2})\.){3}(?:25[0-5]|2[0-4]\d|1?\d{1,2})\b')
    if ($m.Count -gt 0) { return $m[0].Value }
    return $null
}

function Get-FirstWslIPv4_IpAddr {
    try {
        if ($WslDistro) {
            $out = & wsl -d $WslDistro -- ip -4 addr show scope global 2>$null
        } else {
            $out = & wsl -- ip -4 addr show scope global 2>$null
        }
    } catch {
        return $null
    }
    if (-not $out) { return $null }
    $joined = ($out -join "`n")
    $m = [regex]::Match($joined, '\b(?:(?:25[0-5]|2[0-4]\d|1?\d{1,2})\.){3}(?:25[0-5]|2[0-4]\d|1?\d{1,2})\b')
    if ($m.Success) { return $m.Value }
    return $null
}

function Get-FirstWslIPv4 {
    $hv = Get-FirstWslIPv4_Hostname
    if ($hv) { return $hv }
    $fv = Get-FirstWslIPv4_IpAddr
    if ($fv) { return $fv }
    throw "Unable to determine a WSL IPv4 via hostname -I or ip -4 addr. Ensure a distro is running."
}

function Get-PortProxyTable {
    & netsh.exe interface portproxy show all 2>$null
}

function PortProxy-ExistsByListen($listenAddress, $listenPort) {
    $out = Get-PortProxyTable
    if (-not $out) { return $false }
    $token = ("{0}:{1}" -f $listenAddress, $listenPort)
    $esc = [regex]::Escape($token)
    return ($out -match "Listen on\s+:\s*$esc")
}

function PortProxy-ExistsExact($listenAddress, $listenPort, $connectAddress, $connectPort) {
    $out = Get-PortProxyTable
    if (-not $out) { return $false }
    $listenToken = ("{0}:{1}" -f $listenAddress, $listenPort)
    $connectToken = ("{0}:{1}" -f $connectAddress, $connectPort)
    $listenEsc = [regex]::Escape($listenToken)
    $connectEsc = [regex]::Escape($connectToken)
    if ($out -match "Listen on\s+:\s*$listenEsc") {
        $blocks = $out -split "(?=Listen on\s+:)"
        $block = $blocks | Where-Object { $_ -match "Listen on\s+:\s*$listenEsc" }
        if ($block -and ($block -match "Connect to\s+:\s*$connectEsc")) { return $true }
    }
    return $false
}

function Run-NetshAdd($listenAddress, $listenPort, $connectAddress, $connectPort) {
    & netsh.exe interface portproxy add v4tov4 listenport=$listenPort listenaddress=$listenAddress connectport=$connectPort connectaddress=$connectAddress
    return $LASTEXITCODE
}

function Run-NetshDeleteByListen($listenAddress, $listenPort) {
    & netsh.exe interface portproxy delete v4tov4 listenport=$listenPort listenaddress=$listenAddress
    return $LASTEXITCODE
}

function Add-PortProxyIfMissing($listenAddress, $listenPort, $connectAddress, $connectPort) {
    if (PortProxy-ExistsExact $listenAddress $listenPort $connectAddress $connectPort) {
        Write-Output ("Portproxy exists: {0}:{1} -> {2}:{3}" -f $listenAddress, $listenPort, $connectAddress, $connectPort)
        return
    }
    Write-Output ("Adding portproxy: {0}:{1} -> {2}:{3}" -f $listenAddress, $listenPort, $connectAddress, $connectPort)
    $code = Run-NetshAdd $listenAddress $listenPort $connectAddress $connectPort
    if ($code -ne 0) { throw "Failed adding portproxy for port $listenPort (netsh exit $code)." }
}

function Remove-PortProxyIfPresent_ByListen($listenAddress, $listenPort) {
    if (-not (PortProxy-ExistsByListen $listenAddress $listenPort)) {
        Write-Output ("No matching portproxy to remove for listen {0}:{1}" -f $listenAddress, $listenPort)
        return
    }
    Write-Output ("Removing portproxy for listen {0}:{1}" -f $listenAddress, $listenPort)
    $code = Run-NetshDeleteByListen $listenAddress $listenPort
    if ($code -ne 0) { throw "Failed removing portproxy for port $listenPort (netsh exit $code)." }
}

function Ensure-FirewallRule($displayName, $localPort) {
    $existing = Get-NetFirewallRule -DisplayName $displayName -ErrorAction SilentlyContinue
    if ($existing) {
        Write-Output ("Firewall rule exists: {0} (port {1})" -f $displayName, $localPort)
        return
    }
    Write-Output ("Creating firewall rule: {0} (port {1})" -f $displayName, $localPort)
    New-NetFirewallRule -DisplayName $displayName -Direction Inbound -LocalPort $localPort -Protocol TCP -Action Allow -Profile Any | Out-Null
}

function Remove-FirewallRuleIfPresent($displayName) {
    $existing = Get-NetFirewallRule -DisplayName $displayName -ErrorAction SilentlyContinue
    if (-not $existing) {
        Write-Output ("No firewall rule found: {0}" -f $displayName)
        return
    }
    Write-Output ("Removing firewall rule: {0}" -f $displayName)
    $existing | Remove-NetFirewallRule
}

# main
Assert-IsAdmin

$mappings = @(
    @{ ListenPort = 8080; ConnectPort = 8080; FWName = "WSL Spring Boot" }
    @{ ListenPort = 3000; ConnectPort = 3000; FWName = "WSL React Dev" }
)

try {
    $wslIp = Get-FirstWslIPv4
    Write-Output ("Detected WSL IP: {0}" -f $wslIp)

    if ($Remove) {
        foreach ($m in $mappings) {
            # remove by listen port/address (more forgiving)
            Remove-PortProxyIfPresent_ByListen $ListenAddress $m.ListenPort
            Remove-FirewallRuleIfPresent $m.FWName
        }
        Write-Output "Removal completed. Current portproxy table:"
        Get-PortProxyTable
        exit 0
    }

    foreach ($m in $mappings) {
        Add-PortProxyIfMissing $ListenAddress $m.ListenPort $wslIp $m.ConnectPort
        Ensure-FirewallRule $m.FWName $m.ListenPort
    }

    Write-Output "Configuration completed. Current portproxy table:"
    Get-PortProxyTable

} catch {
    Write-Error "ERROR: $_"
    exit 2
}