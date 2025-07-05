# Dynamic Email Generator Service

A flexible, secure RESTful service that generates custom email addresses using a proprietary expression language, secured APIs, and containerized deployment.

---

## Features Implemented (by Technical Challenge)

### 1. Custom Expression Language

Supports:
- `inputN.firstChars(X)`, `lastChars(X)`, `allChars()`
- `toLowerCase()`, `toUpperCase()`
- `concat(...)` function and `~` operator
- Dynamic input parameters (`input1`, `input2`, ..., `inputN`)
- Generates all valid combinations (Cartesian product of inputs)

---

### 2. Documentation

- `README.md`: Full API, Docker, security and usage
- `ExpressionSyntax.md`: DSL reference with examples
- `setup.md`: Step-by-step setup guide (local & Docker)
- Postman collection: `EmailGenerator.postman_collection.json`
- `CHALLENGE.md`: Full technical task description

---

### 3. API Usage

- Base URL (Docker): `https://localhost:9443/api`
- Swagger UI (dev): `http://localhost:8080/swagger-ui.html`
- Example:
```apex
GET /api/email?expression=concat(input1.firstChars(1),input2.lastChars(3),"@test.com")
&input1=Anna,Ivan&input2=Petrova,Georgieva
```
---

### 4. Testing

- JUnit 5 unit + integration tests
- Expression DSL coverage: 100%
- Security + RBAC integration tests with H2
- Run with `@ActiveProfiles("test")`

---

### 5. Persistence

- Relational DB: PostgreSQL (in Docker) or in-memory H2 (for tests/dev)
- Entities:
- `EmailTemplate`: stores expression + name
- `GeneratedEmail`: stores generated email linked to a template
- `AppUser`: user accounts with roles
- Spring Data Repositories and `@Transactional` services
- Seeded test data (via dev/docker profile)

---

### 6. Security

- Spring Security with Basic Auth
- Users (defined in DB):
- **Admin**: `admin / admin123`
- **User**: `user / user123`
- Role-Based Access Control:
- `/api/templates` - Admin-only
- `/api/email`, `/api/emails` - Accessible to authenticated users

---

### 7. Docker + NGINX + HTTPS

- `docker-compose.yml` runs two services:
- `app`: Spring Boot app with Temurin 17
- `nginx`: serves HTTPS (port 9443)
- NGINX:
- Self-signed cert auto-generated if not found
- Redirects HTTP (80) → HTTPS (443)
- Docker volumes:
- NGINX config, certs mounted from `./nginx/`

Access API via: `https://localhost:9443/api`

---

### 8. Git Best Practices

- Public GitHub repository
- Branch naming: `feature/`, `fix/`
- Commits follow conventions
- `.gitignore` included

## Database Setup

| Profile     | DB         | Location              |
|-------------|------------|------------------------|
| `dev`       | H2         | In-memory              |
| `test`      | H2         | In-memory (tests)      |
| `docker`    | PostgreSQL | Docker service         |
| `prod`      | PostgreSQL | Custom URL/config      |

Docker profile uses:
- `emailgen-db:5432`
- `DB_USER`: `emailgen`
- `DB_PASSWORD`: `secret`
- Automatically initialized with schema + sample data

---

## How to Run

### Locally (dev mode)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
With Docker
bash
```
docker-compose up --build
```
Access at:

Swagger UI: https://localhost:9443/swagger-ui.html

Postman Collection
Use the included file:

pgsql

```
EmailGenerator.postman_collection.json
```
Includes example requests (GET/POST)

Requires Basic Auth (user/user123)

Covers all endpoints

Reference

[CHALLENGE](CHALLENGE.md) – full technical assignment

[EmailGenerator-Postman Collection](EmailGenerator.postman_collection.json) – custom DSL functions

[setup](setup.md) – detailed local & Docker setup

© 2025 Dynamic Email Generator by Vladimir Stratiev
