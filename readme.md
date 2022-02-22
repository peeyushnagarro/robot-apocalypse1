# Robot apocalypse
This  project is created using spring boot, spring data jpa, maven and h2 db.

## Build the project
You can build the project by following maven command

`mvn clean install`

## Run the project
You can run the project using below two steps

1. By running following maven command on project root directory

`mvn spring-boot:run`

2. By extracting jar file

you can run below command on target folder of project containing apocalypse jar

`java -jar apocalypse-0.0.1-SNAPSHOT.jar`

## Api Documentation

1. Swagger documentation

you can get api documetation after running the project on below localtion

`{context-path}/robot/apocalypse/swagger-ui/index.html`

example : -

`http://localhost:8080/robot/apocalypse/swagger-ui/index.html`

2. Text Documentation

`{project-root-dir}/api-documentation.txt`

example :- 

`C:\tillster\robot-apocalypse\api-documentation.txt`

## H2 DB console

1. location : -

`{context-path}/robot/apocalypse/h2-ui/`

example :-

`http://localhost:8080/robot/apocalypse/h2-ui/`

2. JDBC URL : -

`jdbc:h2:./data/survivor`

3. User name : -

`survivor`

4. Password : -

`survivor`

