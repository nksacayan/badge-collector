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