# Spring boot demonstrational project 

This project serves for demonstration purposes only. I have prepared this demostrational project to introduce level of skills I have so far.

Built by [Mikhail Vilms](https://www.linkedin.com/in/mikhail-vilms/)

You can check functionality of this project here: https://mvilms-demo-shops-manager.herokuapp.com

(functionality works correctly, but it can takes 20-30 seconds for heroku to run its dyno)

Thank you for checking out this repo!

This project has [separate front-end application built on Angular 2](https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager-ui)

## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- An installed version of [Postgres](http://www.postgresql.org/) to test locally

## Deployment steps

- Clone this repo to your local machine
- Create Postgres database
- Change values of database connection parameters (url/user/password) at {project root directory}/src/main/resources/application.yml
- Execute two scripts on your new db: [first](https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager/blob/Issue-28-Add-Readme/src/main/resources/Setup_Database.sql) for database setup, [second](https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager/blob/Issue-28-Add-Readme/src/main/resources/Populate_Database.sql) to populate its tables with data,
- Execute maven commands at project root directory: "mvn -clean", "mvn -package"
- Run jar file in target directory of project
- Application is ready to be tested by Postman; Try GET request http://localhost:8080/products
- This project have front-end application, check here: https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager-ui

## Project descripion

![Project Illustration](https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager/blob/Issue-28-Add-Readme/src/main/resources/images/Project%20illustration.jpg)

![UML Diagram](https://github.com/Mihail-Vilms-MSU/demo-furniture-shops-manager/blob/Issue-28-Add-Readme/src/main/resources/images/UML%20diagram.jpg)
