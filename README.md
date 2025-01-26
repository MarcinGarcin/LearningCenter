# Learning center
# [https://learningcenter.onrender.com/req/login)

Learning Center is a Spring Boot-based application designed to help users manage their academic or personal tasks efficiently. It allows users to create, organize, and manage notes across multiple subjects while also maintaining a dedicated to-do list for tracking tasks.

## Technologies

The project is built using the following technologies and tools:

- **Spring Boot**: Provides the core framework for creating a modern web application with minimal configuration.
- **Spring Boot Starter Web**: Simplifies building RESTful APIs and handling HTTP requests.
- **Spring Boot Starter Security**: Ensures robust authentication and authorization mechanisms.
- **Spring Boot Starter Data JPA**: Simplifies database operations by abstracting the persistence layer.
- **Spring Boot Starter Mail**: Provides utilities for sending emails within the application.
- **Spring Boot Starter Thymeleaf**: Integrates the Thymeleaf templating engine for rendering dynamic views.
- **Spring Boot Starter Test**: Facilitates testing by including essential testing libraries.
- **Spring Security Test**: Offers tools for testing Spring Security configurations.
- **PostgreSQL**: A robust and scalable relational database system used for persistent data storage.
- **Lombok**: Reduces boilerplate code by automatically generating getters, setters, and other methods through annotations.
- **Spring Dotenv**: Manages environment variables effectively, ensuring a secure and flexible configuration.
- **Maven**: Manages project dependencies and builds through the Spring Boot Maven Plugin.
- **Docker**: The application can be containerized to ensure consistency across environments, making it easy to deploy and scale.
- **Hibernate ORM**: Enhances interaction with the PostgreSQL database, simplifying complex queries and entity relationships.

## Features

- **Task Management**: Allows users to create, update, and delete tasks with additional metadata such as priorities or due dates.
- **Secure User Authentication**: Implements role-based access control using Spring Security.
- **Email Notifications**: Supports sending task-related notifications or updates via email.
- **Database Persistence**: Utilizes PostgreSQL for reliable and structured storage of data.
- **Dynamic Views**: Uses Thymeleaf to render responsive and interactive HTML pages.
- **Environment Variable Management**: Ensures flexibility in configuration using Dotenv.
