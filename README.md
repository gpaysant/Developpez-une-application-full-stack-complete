# MDD App
With this application, the users can subscribe to topic and participate to posts.

This contains front-end and back-end development.

The purpose of this application is to create full stack development.

The main framework and languages are :
- Java
- Spring (boot, security, data JPA)
- Angular
- Typescript

## Getting Started

### Prerequisites
* Maven
* Java 21
* Angular 17
* NodeJS 20
* MySQL

## Install Database 

1. Create Database
  ```sh
  CREATE DATABASE mdd;
  ```
2. Run script SQL 

No need to run script, when you will run api (folder back), the sql will be execute automatically.  

4. Configure informations for your database in applications.properties
 
  Update your environment variable and port of your server.
  ```sh
  spring.datasource.url=jdbc:mysql://localhost:3306/mdd?serverTimezone=UTC
  spring.datasource.username=${DB_USERNAME}
  spring.datasource.password=${DB_PASSWORD}
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=create
  spring.jpa.defer-datasource-initialization=true
  spring.sql.init.mode=always
  
  server.port=9000
  ```

## Install the project

1. Angular application("front" folder)
* Install NPM packages
  ```sh
  npm install
  ```
 
* Builds and serves application
  ```sh
  ng serve
  ```

2. API Application("back" folder)
* Install all dependencies and create jar executable :
  ```sh
  Run ./mvnw clean install
  ```

* Run API project :
  ```sh
  java -jar mdd-api-1.0.0.jar
  ```

## Usage

1. Navigate to : http://localhost:4200/. The main page shows login/register by default.

2. After you login/register , you could subscribe to topic and access to posts.

   The mobile version is available as well.


## Language/Framework
* Mysql/Java/Lombok/Spring boot/Spring Security(JWT authentication)/MapStruct/Spring data JPA
* Angular/Angular Material/Typescript
* Postman

<!-- CONTACT -->
## Contact

Paysant Gérald - geraldpaysant@gmail.com

Project Link : [https://github.com/gpaysant/Developpez-une-application-full-stack-complete](https://github.com/gpaysant/Developpez-une-application-full-stack-complete)
