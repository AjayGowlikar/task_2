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

### Database Setup

Configure the MySQL database connection in the application.properties/application.yml file.

spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

#### Email Setup

Configure the email connection in the application.properties file.

# email properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true









   

  
