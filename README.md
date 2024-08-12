# URL Shortener Application

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.1-blue.svg)](https://spring.io/projects/spring-boot)

## Overview

The **URL Shortener** application is a RESTful web service that allows users to shorten long URLs into compact, shareable links. This service is ideal for managing and simplifying URLs, making them easier to distribute and track. The application is built with Spring Boot and integrates with a MySQL database for persistent storage.

## Features

- **URL Shortening**: Convert long URLs into short, easy-to-share links.
- **Redirection**: Redirect users from a short URL to the original long URL.
- **Expiration Management**: Set expiration dates for short URLs.
- **Exception Handling**: Comprehensive error handling for invalid URLs.
- **Persistence**: Store URL mappings and their metadata in a MySQL database.
- **Security**: Prevent injection attacks and ensure safe URL handling.

## Architecture

The application follows a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Implements business logic and URL management.
- **Repository Layer**: Interfaces with the MySQL database using Spring Data JPA.
- **Model Layer**: Defines the data structures for URL mappings.

## Getting Started

### Prerequisites

- **Java 17** or later
- **Maven 3.8+** for dependency management
- **MySQL 8.0+** for database


### Backend Setup

1. Clone the repository:
    ```bash
    https://github.com/kanhaiyatiwari/live-voice-translator-app.git
    ```

2. Configuration
Update the application.properties file with your MySQL configuration:
 ```spring.datasource.url=jdbc:mysql://localhost:3306/urlshortener
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
3. Build and Run
To build and run the application, use the following commands:
    ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Access the Application
Once the application is running, you can access the service at:
    ```bash
    http://localhost:8080
    ```
4. Access the Application
Once the application is running, you can access the service at:
    ```bash
    http://localhost:8080
    ```
