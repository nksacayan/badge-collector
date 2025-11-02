# patch-collector-client
Patch collector client for bday project

Don't forget to use nvm to sync node across workspace and use good npm package habits

Use sdkman for backend version manager

Use WSL at home for compatability with nvm

To start the backend in dev:
mvn clean install
mvn spring-boot:run
localhost:8080 is default

To start the frontend in dev:
npm run dev

To make wsl -> windows -> lan reachable u gotta route wsl to windows and then make the firewall rules. Will have to do on any dev device. Might be easier at the end when im using a build binary

1)
wsl hostname -I

netsh interface portproxy add v4tov4 listenport=8080 listenaddress=0.0.0.0 connectport=8080 connectaddress=<WSL_IP>
netsh interface portproxy add v4tov4 listenport=3000 listenaddress=0.0.0.0 connectport=3000 connectaddress=<WSL_IP>
netsh interface portproxy show all

skip firewalling i think i've done in manually but have an automated check
New-NetFirewallRule -DisplayName "WSL Spring Boot" -Direction Inbound -LocalPort 8080 -Protocol TCP -Action Allow
New-NetFirewallRule -DisplayName "WSL React Dev" -Direction Inbound -LocalPort 3000 -Protocol TCP -Action Allow

automate this later it doesnt persist between boots

2) write to the env
.env is on the repo flmao dont put anything bad in there

you also have to change the env every startup since ip is dynamic

run write_ip sh
copy the ps script to /mnt/ then run in windows elevated

For spring side:
spring.profiles.active=dev

For react side:
VITE_DEV_MODE=true