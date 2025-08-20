# Notification Service

A microservice for handling notifications, built with Spring Boot.

## Tech Stack

- Java 17+
- Spring Boot
- Spring Security (OAuth2)
- Spring Data JPA (Hibernate)
- MySQL
- Apache Kafka
- Zipkin (Distributed Tracing)
- Maven
- Keycloak (OAuth2 Provider)
- Docker (for Zipkin/Keycloak)

## Setup

1. Clone the repository.
2. Configure `application.properties` for MySQL, Kafka, Zipkin, and Keycloak.
3. Start supporting services:
    - MySQL
    - Kafka
    - Zipkin:  
      `docker run -d -p 9411:9411 openzipkin/zipkin`
    - Keycloak:  
      `docker run -d -p 8080:8080 jboss/keycloak`
4. Build and run the application:
## Features

- OAuth2 authentication via Keycloak
- Kafka-based event processing
- MySQL persistence
- Distributed tracing with Zipkin