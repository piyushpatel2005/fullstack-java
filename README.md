# Fullstack Java Development

## Pre-requisites

- Java 17
- Maven 3.8.3
- Docker
- Docker Compose

## Setup

1. Clone the repository
2. Run the following command to start the database
```shell
docker-compose up -d
```
3. Run the following command to start the application
```shell
mvn spring-boot:run
```
4. Access the application at http://localhost:8080