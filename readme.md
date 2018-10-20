# App: Coin Viewer

This repo contains 2 projects, a backend and a frontend.

## Backend:
Is a java spring-boot maven project using h2 (in-memory) database. To run it, you can use the windows cmd command:
> mvn spring-boot:run

After waiting for it to load (check cmd window), its root can be found by visiting http://localhost:8080/ which could be used to check the endpoint /products and evaluate if the backend is working or not.

## Frontend:
After download and unzip it, run the following npm command:
> npm install

It should update / install all node modules and if all goes well, simply run gulp to launch an integrated server instance which is provided.
The command to run it is:
> gulp serve

This command will provide a server instance running at http://localhost:3000 and index.html file should be loaded by default.

Notes (important!): 
- The backend requires java 8 to run and the maven should setup all required dependencies;
- It's important to use the port 3000 for frontend because the spring-boot project has CORS enabled to this url:port only;
