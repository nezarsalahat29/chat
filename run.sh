#!/bin/bash

# Start Docker containers
docker compose up -d

# Wait for containers to be ready
echo "Waiting for MySQL and RabbitMQ to be ready..."
sleep 10

# Start Spring Boot application
mvn spring-boot:run
