# Job Application (Spring Boot)

A simple Spring Boot backend for managing job applications, users, and application notes.

## Tech Stack
- Java 17
- Spring Boot
- Spring WebMVC
- Spring Data JPA
- MySQL

## Project Structure
- `src/main/java/com/example/demo/controller` REST controllers
- `src/main/java/com/example/demo/service` business logic
- `src/main/java/com/example/demo/Repository` JPA repositories
- `src/main/java/com/example/demo/entity` JPA entities
- `src/main/java/com/example/demo/DTO` request/response DTOs

## Setup
1. Create a MySQL database (e.g., `job`).
2. Add your local DB settings to `src/main/resources/application.properties`.
3. Run the app:

```powershell
./mvnw spring-boot:run
```

## IMPORTANT
**`src/main/resources/application.properties` IS NOT INCLUDED IN THIS REPO.**
It is intentionally ignored because it can contain secrets (like DB credentials).
You must create it locally before running the app.

