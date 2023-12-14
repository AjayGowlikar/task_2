# Spring Boot 3.2.0 Project with User Authentication and Password Reset

This project is a Spring Boot application with user authentication and password reset functionality. It uses the following dependencies:

- Java Mail Sender
- Spring Web
- Dev Tools
- Starter Validation
- MySQL Driver
- Spring Data JPA

## Getting Started

To create the project, follow these steps:

1. Go to [https://start.spring.io/](https://start.spring.io/) and generate a project with the following settings:
   - Project: Maven
   - Language: Java
   - Spring Boot: 3.2.0
   - Dependencies: Java Mail Sender, Spring Web, Spring Security, Dev Tools, Starter Validation, MySQL Driver, Spring Data JPA

2. Clone the generated repository and import it into your preferred IDE.




### Entity Definition

Create an `@Entity` class to represent user details and store them in the MySQL database.

```java
@Entity
@Table(name = "user_dtls")
public class User {

    // Fields: userId, email, firstName, lastName, gender, password, resetPasswordToken, roles

    // Constructors, getters, setters, and other methods

}

## Database Setup




##Database Configuration

Configure the MySQL database connection in the application.properties/application.yml file.

spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password







   

  
