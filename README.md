# Bank Transaction Management System

A simple Spring Boot application for managing banking transactions with in-memory storage.

## Features

- Create, read, update, and delete transactions
- Filter transactions by account number
- Caching for improved performance
- Comprehensive error handling
- Containerized with Docker

## Technologies

- Java 21
- Spring Boot 3.1.5
- Caffeine for caching
- Maven for dependency management
- Docker for containerization
- Snowflake for unique id

## External Libraries

- **Lombok**: For reducing boilerplate code (optional)
- **Caffeine**: For caching implementation

## API Endpoints

- `POST /api/transactions`: Create a new transaction
- `GET /api/transactions`: Get all transactions
- `GET /api/transactions/{id}`: Get a transaction by ID
- `GET /api/transactions/account/{accountNumber}`: Get transactions by account number
- `PUT /api/transactions/{id}`: Update a transaction
- `DELETE /api/transactions/{id}`: Delete a transaction

## Running the Application

### Prerequisites

- Java 21 JDK
- Maven
- Docker

### Running Locally

1. Clone the repository
2. Build the project: `mvn clean install`
3. Run the application: `mvn spring-boot:run`

### Running with Docker

1. Build the Docker image: `docker-compose build`
2. Start the containers: `docker-compose up`

The application will be available at `http://localhost:8080`

## Testing

Run unit tests with: `mvn test`

## Stress Testing

You can use tools like Apache JMeter or wrk to perform stress testing on the API endpoints.