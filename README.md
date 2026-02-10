# WinWin Travel Test Project

This project contains two Spring Boot microservices:

1. **auth-api** – Authentication service (handles registration, login, JWT).
2. **data-api** – Data processing service (transforms text).

Both services can be run locally or with Docker.

---

## Prerequisites

- Java 21+
- Maven (optional if using `mvnw`)
- Docker & Docker Compose
- PostgreSQL (required only for local run without Docker)

---

## Environment Variables (.env)

Create a `.env` file in the project root with:

```dotenv
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=testdb
INTERNAL_TOKEN=secret-token
SERVICE_B_URL=http://data-api:8081
JWT_SECRET=<your-32+ chars secret>
```

---

## Running with Docker Compose

From the project root (where `docker-compose.yml` is located), run:

```bash
docker compose up --build -d
```

Services will run on:

- **auth-api** → http://localhost:8080
- **data-api** → http://localhost:8081
- **PostgreSQL** runs automatically in its container

Stop all services:

```bash
docker compose down
```

**Notes:**

- The `.env` file is required for Docker Compose to inject configuration into containers.
- No local PostgreSQL installation is needed when running with Docker Compose.
- Ensure `JWT_SECRET` has at least 32 characters.
- Internal requests from auth-api to data-api use `X-Internal-Token` set via `.env`.

---

## Running Locally (without Docker)

### auth-api

```bash
cd auth-api
./mvnw clean package
java -jar target/auth-api-0.0.1-SNAPSHOT.jar
```

### data-api

```bash
cd data-api
./mvnw clean package
java -jar target/data-api-0.0.1-SNAPSHOT.jar
```

> Make sure your PostgreSQL is running and env variables are set.

---

## Testing Endpoints

### 1. Register a user

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"password123"}'
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"password123"}'
```

Response:

```json
{
  "token": "<jwt-token>"
}
```

### 3. Call processing endpoint (service A → service B)

```bash
curl -X POST http://localhost:8080/api/process \
-H "Authorization: Bearer <jwt-token>" \
-H "Content-Type: application/json" \
-d '"hello world"'
```

Response example:

```json
{
  "text": "HELLO WORLD"
}
```

---

## Notes

- auth-api uses JWT for authentication.
- data-api transforms text to uppercase.
- Internal requests from auth-api to data-api are authenticated using `X-Internal-Token`.
- For production, consider creating a shared DTO module to avoid duplicity