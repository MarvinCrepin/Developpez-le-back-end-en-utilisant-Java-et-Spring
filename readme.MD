# README - Java Spring Boot Application with MySQL Database

This repository contains a Java Spring Boot application that utilizes a MySQL database to store data.

## Prerequisites

Before getting started, ensure you have the following installed:

- Java Development Kit (JDK) - version 8 or higher
- MySQL Server - version 5.7 or higher
- Maven - for building and managing dependencies
- Your favorite Integrated Development Environment (IDE) - e.g., IntelliJ, Eclipse, or Visual Studio Code

## Setting up the MySQL Database

1. First, make sure you have MySQL Server installed and running on your machine.

2. Create a new database for the application. You can use the MySQL command-line interface or a GUI tool like MySQL Workbench to execute the following SQL script provided in the repository:

```sql
-- Create a new database for the application
CREATE DATABASE your_database_name;


-- Locate app\ressources\sql\script.sql

Execute this script to have the database schema


> ## Configuring the Application
>
> 1. Open the `application.properties` file located in the `src/main/resources` directory of the project.
>
> 2. Update the following properties with your MySQL database credentials:
>
> ```properties
> spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
> spring.datasource.username=your_database_username
> spring.datasource.password=your_database_password
> ```
>
> Replace `your_database_name`, `your_database_username`, and `your_database_password` with the appropriate values corresponding to your MySQL setup.
>
> ## Building and Running the Application
>
> 1. Open the project in your preferred IDE.
>
> 2. Build the project using Maven to download the required dependencies:
>
> ```bash
> mvn clean install
> ```
>
> 3. Run the Spring Boot application:
>
> ```bash
> mvn spring-boot:run
> ```
>
> The application should now be running on `http://localhost:8080`, and you can access its endpoints through a web browser or API client like Postman.
>
> ## Docs
> - Swagger URL : http://localhost:8080/swagger-ui/index.html
> 
> ## Additional Notes
>
> - If you encounter any issues during the setup or execution, please check the troubleshooting section in this README or refer to the Spring Boot and MySQL documentation.
>
> - Remember that this README serves as a quick guide to get started with the application. In a real-world scenario, you should consider additional security measures and best practices, especially when deploying the application to production.
