# Teclab Backend - H2 + JWT + Tasks

## Requisitos
- Java 17
- Maven

## Cómo correr
```bash
mvn spring-boot:run
```

## Endpoints
- POST /auth/login  (body: {"username":"admin","password":"admin123"})
- GET /tasks  (Auth: Bearer <token>)
- POST /tasks
- PUT /tasks/{id}
- DELETE /tasks/{id}

H2 console: /h2  
Swagger UI: /swagger-ui.html
