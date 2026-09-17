# 🐾 VetSystem — Sistema de Gestión de Clínica Veterinaria

Proyecto integrador de la materia electiva **Microservicios y APIs Escalables** de la
**Facultad de Ingeniería — Universidad de Palermo** (2026).

El sistema modela la gestión de la clínica veterinaria *"Patitas Felices"*: dueños,
mascotas, veterinarios y turnos. Se construye de forma incremental, **un sprint por clase**,
evolucionando desde un **monolito MVC** hasta una **arquitectura de microservicios** completa.

> 📌 Este repositorio sigue el estándar **IEEE 29148:2018** para la especificación de
> requisitos (ver [SRS](02_SRS_IEEE29148_VetSystem.docx)) y un cronograma ágil inspirado en
> Scrum (ver [Cronograma](01_Cronograma_Plan_Trabajo.docx)).

---

## 📋 Índice

- [Descripción](#-descripción)
- [Stack tecnológico](#-stack-tecnológico)
- [Dominio](#-dominio)
- [Cómo ejecutar el proyecto](#-cómo-ejecutar-el-proyecto)
- [Frontend (package nuevo)](#-frontend-package-nuevo)
- [Endpoints disponibles](#-endpoints-disponibles)
- [Estado del proyecto (sprints)](#-estado-del-proyecto-sprints)
- [Requisitos](#-requisitos)
- [Convenciones de trabajo](#-convenciones-de-trabajo)
- [Autores](#-autores)

---

## 📝 Descripción

**VetSystem** es un sistema web de gestión integral para clínicas veterinarias. Permite:

- Registrar y administrar **dueños** y sus **mascotas**.
- Gestionar **veterinarios** y su disponibilidad.
- Agendar y controlar **turnos**, incluyendo el historial médico de cada mascota.

El proyecto se desarrolla en **dos fases**:

1. **Fase 1 — Monolito MVC** (Sprints 1 a 7): arquitectura en capas
   `Controller → Service → Repository` con persistencia JPA sobre MySQL.
2. **Fase 2 — Microservicios** (Sprints 9 a 14): descomposición en microservicios con
   Spring Cloud (Eureka, Config Server, Gateway, OpenFeign), seguridad JWT, resiliencia
   con Resilience4J, caché con Redis, historial en MongoDB y orquestación con Docker Compose.

---

## 🛠 Stack tecnológico

### Fase actual (Monolito MVC)

| Tecnología | Versión | Propósito |
|---|---|---|
| Java | 21 | Lenguaje base |
| Spring Boot | 4.1.0 | Framework backend |
| Spring MVC / REST | via Spring | API REST (`@RestController`) |
| Spring Data JPA / Hibernate | via Spring | Persistencia relacional |
| MySQL | 8.x | Base de datos relacional |
| Lombok | — | Reducción de boilerplate |
| MapStruct | 1.5.5 | Mapeo Entity ↔ DTO |
| Bean Validation | via Spring | Validaciones de entrada (`@Valid`) |
| JUnit 5 + Mockito + MockMvc | via Spring Boot Test | Testing unitario y de controladores |
| Maven | — | Gestión de dependencias y build |
| Git / GitHub | — | Control de versiones (una rama por sprint) |

### Fase 2 (planificada)

Bootstrap 5 · MongoDB 7 · Redis 7 · Eureka · Spring Cloud Config · Spring Cloud Gateway ·
OpenFeign · Spring Security + JWT · Actuator + Micrometer · Docker / Compose ·
Swagger/OpenAPI 3.

---

## 🗂 Dominio

| Entidad | Atributos principales | Relaciones |
|---|---|---|
| **Dueño** | nombre, apellido, dni, teléfono, email | Tiene muchas Mascotas |
| **Mascota** | nombre, especie, raza, fechaNacimiento | Pertenece a un Dueño · tiene muchos Turnos |
| **Veterinario** | nombre, apellido, matrícula, especialidad | Atiende muchos Turnos |
| **Turno** | fecha, hora, motivo, estado, observaciones | Tiene una Mascota y un Veterinario |

---

## 🚀 Cómo ejecutar el proyecto

> El código de cada sprint vive en su propia carpeta (`Clase 2- Rest CRUD Duenio`,
> `Clase 3- CRUD Mascota con JSON`, etc.). Para ejecutar la versión más reciente, ubicate en
> la carpeta del último sprint.

### Requisitos previos

- **JDK 21**
- **Maven 3.9+** (o usar el wrapper `mvnw` incluido)
- **MySQL 8** corriendo en `localhost:3306`

### 1. Crear la base de datos

```sql
CREATE DATABASE vet_system;
```

### 2. Configurar credenciales

Verificá la configuración en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vet_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create
server.port=8080
```

> ⚠️ Ajustá `username` / `password` a tu instalación local de MySQL.

### 3. Ejecutar la aplicación

```bash
# Desde la carpeta del sprint, por ejemplo:
cd "Clase 3- CRUD Mascota con JSON/vet-system"

# Windows
mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

La API queda disponible en **http://localhost:8080**.

---

## 🖥 Frontend (package nuevo)

A partir del **Sprint 7** el proyecto incorpora un **frontend** en una carpeta
(*package*) **nueva e independiente** del código Java:

```
Clase 7- Swagger y FrontEnd/vet-system/
├── src/                 # Backend Spring Boot (Java)
└── frontend/            # 👈 FRONTEND NUEVO
    ├── index.html       # UI con Bootstrap 5 (CDN) + JS vanilla + Fetch API
    └── GUIA_FRONTEND.txt # 📄 Guía paso a paso para estudiantes
```

- **No se instala nada con npm**: Bootstrap 5 se carga por **CDN**.
- Se abre con la extensión **Live Server** de VS Code (click derecho sobre
  `frontend/index.html` → *Open with Live Server*).
- El frontend consume la API en `http://localhost:8080` (constante `API_BASE`).
- **CORS ya está resuelto** (clase `CorsConfig` en `config/`), así que la pantalla
  de Dueños conecta y funciona apenas se levanta el backend.

El frontend viene **funcional solo para la entidad Dueño**, y únicamente con los
métodos ya programados en el controller (**listar** y **crear**). Sirve como
**ejemplo de referencia** para que los estudiantes repliquen el resto.

### ⚠️ Estudiantes: qué tienen que implementar

El detalle completo, con código de ejemplo, está en:

> 📄 **[`Clase 7- Swagger y FrontEnd/vet-system/frontend/GUIA_FRONTEND.txt`](Clase%207-%20Swagger%20y%20FrontEnd/vet-system/frontend/GUIA_FRONTEND.txt)**

Resumen del trabajo a realizar:

1. **Dueño**: implementar los métodos que faltan en el controller —**`PUT`
   (editar)** y **`DELETE` (eliminar)**— y agregar sus botones en el frontend.
2. **Veterinario**: completar el CRUD en el backend (faltan `PUT` y `DELETE`) y
   construir su pantalla en el frontend (tabla + alta + editar + eliminar).
3. **Mascota**: completar el CRUD en el backend (faltan `GET /{id}`, `PUT` y
   `DELETE`) y construir su pantalla en el frontend, con **combo de Dueño**.

> **Turno** queda fuera del alcance de esta entrega (salvo indicación del docente).

---

## 🔌 Endpoints disponibles

> Estado a la fecha del Sprint 7. ✅ = ya implementado (docente) · 🧑‍🎓 = **tarea del alumno**.

### Dueños — `/api/duenio`

| Método | Ruta | Descripción | Estado |
|---|---|---|---|
| `GET` | `/api/duenio` | Listar todos los dueños | ✅ |
| `GET` | `/api/duenio/{id}` | Buscar dueño por ID | ✅ |
| `POST` | `/api/duenio` | Registrar un nuevo dueño (valida el DTO) | ✅ |
| `PUT` | `/api/duenio/{id}` | Editar un dueño | 🧑‍🎓 |
| `DELETE` | `/api/duenio/{id}` | Eliminar un dueño | 🧑‍🎓 |

### Veterinarios — `/api/veterinario`

| Método | Ruta | Descripción | Estado |
|---|---|---|---|
| `GET` | `/api/veterinario` | Listar todos los veterinarios | ✅ |
| `GET` | `/api/veterinario/{id}` | Buscar veterinario por ID | ✅ |
| `POST` | `/api/veterinario` | Registrar un nuevo veterinario (valida el DTO) | ✅ |
| `PUT` | `/api/veterinario/{id}` | Editar un veterinario | 🧑‍🎓 |
| `DELETE` | `/api/veterinario/{id}` | Eliminar un veterinario | 🧑‍🎓 |

### Mascotas — `/api/mascota`

| Método | Ruta | Descripción | Estado |
|---|---|---|---|
| `GET` | `/api/mascota` | Listar todas las mascotas *(devuelve Entity, refactor → DTO)* | ✅ |
| `POST` | `/api/mascota` | Registrar una nueva mascota | ✅ |
| `GET` | `/api/mascota/{id}` | Buscar mascota por ID | 🧑‍🎓 |
| `PUT` | `/api/mascota/{id}` | Editar una mascota | 🧑‍🎓 |
| `DELETE` | `/api/mascota/{id}` | Eliminar una mascota | 🧑‍🎓 |

### Turnos — `/api/turnos` *(fuera del alcance de esta entrega)*

| Método | Ruta | Descripción | Estado |
|---|---|---|---|
| `POST` | `/api/turnos` | Agendar un turno (valida existencia y superposición) | ✅ |

> El manejo de errores es centralizado vía `@ControllerAdvice` (`GlobalException`), que
> traduce las excepciones de dominio a respuestas HTTP (`404 Not Found`, `409 Conflict`).

Podés probar los endpoints con **Swagger UI** (`http://localhost:8080/swagger-ui/index.html`),
**Postman** o `curl`.

---

## 📈 Estado del proyecto (sprints)

### Fase 1 — Monolito MVC

| Sprint | Tema | Estado |
|---|---|---|
| 1 | Kickoff + dominio + setup del proyecto | ✅ Completado |
| 2 | Arquitectura MVC + REST · CRUD de Dueño | ✅ Completado |
| 3 | Persistencia JPA + relaciones · CRUD de Mascota | ✅ Completado |
| 4 | CRUD Turno + Veterinario + DTOs (MapStruct / Lombok) | ✅ Completado |
| 5 | Validaciones + manejo de errores (`@ControllerAdvice`) | ✅ Completado |
| 6 | Testing: JUnit 5 + Mockito | ✅ Completado |
| 7 | Swagger + Bootstrap UI (frontend nuevo) + CRUD completo | 🚧 En progreso |
| — | 🎯 **Parcial 1** | ⬜ Pendiente |

### Fase 2 — Microservicios

| Sprint | Tema | Estado |
|---|---|---|
| 9 | ¿Por qué microservicios? + Arquitectura Hexagonal | ⬜ Pendiente |
| 10 | Eureka + Config Server + Actuator | ⬜ Pendiente |
| 11 | API Gateway + Resilience4J (Circuit Breaker) | ⬜ Pendiente |
| 12 | Spring Security + JWT | ⬜ Pendiente |
| 13 | OpenFeign — comunicación entre microservicios | ⬜ Pendiente |
| 14 | Redis + MongoDB + Docker Compose | ⬜ Pendiente |
| — | 🎤 **Presentación oral** | ⬜ Pendiente |

---

## ✅ Requisitos

El sistema implementa **16 requisitos funcionales (RF)** y **11 no funcionales (RNF)**
especificados según IEEE 29148. Resumen:

**Funcionales:** gestión de dueños (RF-01 a RF-04), mascotas (RF-05 a RF-07),
veterinarios (RF-08, RF-09), turnos (RF-10 a RF-13), autenticación/autorización
(RF-14, RF-15) y resiliencia con Circuit Breaker (RF-16).

**No funcionales:** tiempo de respuesta < 500ms, caché Redis (TTL 60s), aislamiento de
fallos, health checks, JWT, externalización de credenciales, arquitectura hexagonal,
cobertura de tests ≥ 70%, documentación OpenAPI y despliegue con Docker Compose.

📄 Detalle completo en el documento [SRS (IEEE 29148)](02_SRS_IEEE29148_VetSystem.docx).

---

## 🤝 Convenciones de trabajo

- **Metodología:** ágil (inspirada en Scrum) — cada clase es un sprint con entregable funcional.
- **Ramas:** una rama por sprint (`sprint-01`, `sprint-02`, …, `sprint-14`).
- **Entregas:** cada sprint se integra a `main` mediante Pull Request.
- **Commits:** `feat:` / `fix:` / `refactor:` / `test:` + descripción en español.
- **Definition of Done:** el código compila, los tests pasan, los endpoints responden
  correctamente, está commiteado en la rama del sprint y el README está actualizado.

---

## 👥 Autores

Trabajo práctico integrador desarrollado en pareja para la materia
**Microservicios y APIs Escalables**.

- Docente / Ingeniero de Software: **Ing. Jorge Agustín Pereyra**
- Universidad de Palermo — Facultad de Ingeniería — 2026
