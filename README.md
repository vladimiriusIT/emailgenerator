Dynamic Email Generator Service

A flexible, secure RESTful service that generates custom email addresses using a proprietary expression language, secured APIs, and containerized deployment.

Features Implemented (by Technical Challenge)

1. Custom Expression Language

Supports:

inputN.firstChars(X), lastChars(X), allChars()

toLowerCase(), toUpperCase()

concat(...) function and ~ operator

Dynamic input parameters (input1, input2, ..., inputN)

Generates all valid combinations (Cartesian product of inputs)

2. Documentation

README.md: Full API, Docker, security and usage

ExpressionSyntax.md: DSL reference with examples

setup.md: Step-by-step setup guide

Postman collection included for testing

4. Testing

Unit and integration tests with JUnit 5

Edge cases, expression DSL, repository + controller coverage

Spring Security integration tests with RBAC

5. Persistence

PostgreSQL (or H2 for tests/dev)

JPA entities: EmailTemplate, GeneratedEmail, AppUser

Spring Data Repositories + @Transactional service layer

Initial seed data in dev/docker profiles

6. Security

Basic Authentication (admin/admin123, user/user123)

Role-based authorization (RBAC)

Endpoint protection per role

7. Docker + NGINX + HTTPS

docker-compose.yml with:

eclipse-temurin:17 for Spring Boot

nginx:latest with HTTPS on 9443

Self-signed cert auto-generated on first build

HTTP to HTTPS redirect

8. Git Best Practices

GitHub repo with:

Meaningful branch names (feature/)

Structured commits, .gitignore

