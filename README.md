[README_teclab_backend.md](https://github.com/user-attachments/files/23197705/README_teclab_backend.md)
# 🧠 Teclab Backend – Spring Boot + H2 + JWT

Backend desarrollado para el **Desafío Full Stack – Vue 3 + Spring Boot**.  
Provee una API REST para autenticación y gestión de tareas, con base de datos H2 en memoria y seguridad mediante JWT.

---

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.3.4**
- **Spring Data JPA / Hibernate**
- **Spring Security + JWT**
- **H2 Database** (en memoria)
- **Lombok**
- **Swagger / OpenAPI** (documentación API)
- **Maven**

---

## ⚙️ Requisitos

- **Java 21**  
- **Maven 3.9+**

---

## 🏃‍♀️ Ejecución local

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/teclab-backend.git
cd teclab-backend

# Compilar y ejecutar
mvn spring-boot:run
```

La aplicación quedará disponible en  
👉 `http://localhost:8080`

---

## 💾 Base de datos (H2)

- **Consola H2:** [http://localhost:8080/h2](http://localhost:8080/h2)  
- **URL:** `jdbc:h2:mem:demo`  
- **Usuario:** `sa`  
- **Password:** *(vacío)*

Al iniciar, se crea automáticamente un usuario administrador:

| Usuario | Contraseña |
|----------|-------------|
| `admin`  | `admin123` |

---

## 🔐 Autenticación JWT

1. **Login**
   ```http
   POST /auth/login
   Content-Type: application/json

   {
     "username": "admin",
     "password": "admin123"
   }
   ```

   ✅ Respuesta
   ```json
   {
     "token": "eyJhbGciOi...",
     "type": "Bearer"
   }
   ```

2. Usar el token en cada request:
   ```
   Authorization: Bearer <token>
   ```

---

## 📚 Endpoints principales

| Método | Ruta | Descripción |
|---------|------|-------------|
| POST | `/auth/login` | Autenticación de usuario y emisión de JWT |
| GET | `/tasks` | Listar tareas (con filtros y paginación) |
| POST | `/tasks` | Crear tarea |
| PUT | `/tasks/{id}` | Actualizar tarea |
| DELETE | `/tasks/{id}` | Eliminar tarea |

Parámetros de búsqueda:
```
GET /tasks?status=PENDIENTE&title=algo&page=0&size=10
```

---

## 🧩 Documentación Swagger

Una vez en ejecución:

- Swagger UI → [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- OpenAPI JSON → `/api-docs`

---

## 🧠 Estructura del proyecto

```
src/
 └── main/
     ├── java/com/example/demo/
     │   ├── controller/      # Controladores REST
     │   ├── service/         # Lógica de negocio
     │   ├── repository/      # Acceso a datos JPA
     │   ├── model/           # Entidades y enums
     │   ├── security/        # Configuración JWT y filtros
     │   └── exception/       # Manejo global de errores
     └── resources/
         └── application.properties
```

---

## ✅ Cumplimiento de requisitos (Desafío Full Stack)

- Estructura en capas (Controller → Service → Repository)  
- Base de datos H2 (JPA/Hibernate)  
- Seguridad JWT con Spring Security  
- Endpoints REST (`/auth/login`, `/tasks`)  
- Manejo centralizado de excepciones (`@ControllerAdvice`)  
- CORS habilitado para Vue 3 (`http://localhost:5173`)  
- Documentación con Swagger/OpenAPI  
- Compatible con **Java 21**

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para el Desafío Full Stack de **TECLAB**.
