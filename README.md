# MTG Card Collection Manager

Backend REST API component for [card-lender-bot](https://github.com/MLcraft/card-lender-bot)

Written in Java 17 using Springboot with PostgreSQL, Redis, and RabbitMQ, containerized with Docker Compose

---

## Build and Run

The project runs in Docker containers, ensure Docker ([Install Docker](https://docs.docker.com/engine/install/)) is installed and running.

To run the project, run the following commands to build and start the docker containers. This will set up the API on port 8080 as well as the PostgreSQL, Redis, and RabbitMQ instances on their default ports.

`./mvnw dependency:resolve`

`./mvnw clean package`

`docker-compose up --build --force-recreate --remove-orphans`

The API specification is available through swagger at `http://localhost:8080/v3/api-docs`

For instructions on how to set up the discord bot this API was written for, see the readme in the [bot repo](https://github.com/MLcraft/card-lender-bot)
