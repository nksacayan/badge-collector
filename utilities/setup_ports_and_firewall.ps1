<#
configure-wsl-portproxy.ps1
- Run as Administrator
- By default adds portproxy + firewall rules for WSL IP detected via `wsl hostname -I`
- Use -Remove to delete the portproxy entries and firewall rules
#>

param(
    [switch]$Remove,
    [string]$ListenAddress = "0.0.0.0"
)

function Assert-IsAdmin {
    $isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
    if (-not $isAdmin) {
        Write-Error "Administrator privileges required. Rerun in an elevated session."
        exit 1
    }
}

function Get-FirstWslIPv4 {
    $raw = & wsl hostname -I 2>$null
    if (-not $raw) { throw "Unable to get WSL IP. Ensure WSL is installed and a distro is running." }
    $parts = $raw -split '\s+' | Where-Object { $_ -match '^\d{1,3}(\.\d{1,3}){3}$' }
    if (-not $parts -or $parts.Count -eq 0) { throw "No IPv4 address found in WSL output: '$raw'." }
    return $parts[0].Trim()
}

function Get-PortProxyTable {
    & netsh interface portproxy show all 2>$null
}

function PortProxy-Exists($listenAddress, $listenPort, $connectAddress, $connectPort) {
    $out = Get-PortProxyTable
    if (-not $out) { return $false }
    $patternListen = "Listen on\s+:\s*$listenAddress:$listenPort"
    if ($out -match $patternListen) {
        $blocks = $out -split "(?=Listen on\s+:)"
        $block = $blocks | Where-Object { $_ -match $patternListen }
        if ($block -and ($block -match "Connect to\s+:\s*$connectAddress:$connectPort")) { return $true }
    }
    return $false
}

function Add-PortProxyIfMissing($listenAddress, $listenPort, $connectAddress, $connectPort) {
    if (PortProxy-Exists $listenAddress $listenPort $connectAddress $connectPort) {
        Write-Output "Portproxy exists: $listenAddress:$listenPort -> $connectAddress:$connectPort"
        return
    }
    Write-Output "Adding portproxy: $listenAddress:$listenPort -> $connectAddress:$connectPort"
    $cmd = "interface portproxy add v4tov4 listenport=$listenPort listenaddress=$listenAddress connectport=$connectPort connectaddress=$connectAddress"
    & netsh $cmd
    if ($LASTEXITCODE -ne 0) { throw "Failed adding portproxy for port $listenPort." }
}

function Remove-PortProxyIfPresent($listenAddress, $listenPort, $connectAddress, $connectPort) {
    if (-not (PortProxy-Exists $listenAddress $listenPort $connectAddress $connectPort)) {
        Write-Output "No matching portproxy to remove for listen $listenAddress:$listenPort"
        return
    }
    Write-Output "Removing portproxy: $listenAddress:$listenPort -> $connectAddress:$connectPort"
    $cmd = "interface portproxy delete v4tov4 listenport=$listenPort listenaddress=$listenAddress"
    & netsh $cmd
    if ($LASTEXITCODE -ne 0) { throw "Failed removing portproxy for port $listenPort." }
}

function Ensure-FirewallRule($displayName, $localPort) {
    $existing = Get-NetFirewallRule -DisplayName $displayName -ErrorAction SilentlyContinue
    if ($existing) {
        Write-Output "Firewall rule exists: $displayName (port $localPort)"
        return
    }
    Write-Output "Creating firewall rule: $displayName (port $localPort)"
    New-NetFirewallRule -DisplayName $displayName -Direction Inbound -LocalPort $localPort -Protocol TCP -Action Allow -Profile Any | Out-Null
}

function Remove-FirewallRuleIfPresent($displayName) {
    $existing = Get-NetFirewallRule -DisplayName $displayName -ErrorAction SilentlyContinue
    if (-not $existing) {
        Write-Output "No firewall rule found: $displayName"
        return
    }
    Write-Output "Removing firewall rule: $displayName"
    $existing | Remove-NetFirewallRule
}

# Main
Assert-IsAdmin

$mappings = @(
    @{ ListenPort = 8080; ConnectPort = 8080; FWName = "WSL Spring Boot" }
    @{ ListenPort = 3000; ConnectPort = 3000; FWName = "WSL React Dev" }
)

try {
    $wslIp = Get-FirstWslIPv4
    Write-Output "Detected WSL IP: $wslIp"

    if ($Remove) {
        foreach ($m in $mappings) {
            Remove-PortProxyIfPresent $ListenAddress $m.ListenPort $wslIp $m.ConnectPort
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