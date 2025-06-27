# Launchit
GTM Strategy application built with Spring Boot.

## Database

The project includes a PostgreSQL schema located at `src/main/resources/schema.sql`.
Execute this script using a PostgreSQL client to create the necessary tables.

## Authentication

APIs require a valid JWT for access. Configure the secret key via the
`jwt.secret` property in `application.properties` or as an environment
variable.
