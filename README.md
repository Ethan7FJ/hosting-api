# Hosting Management System

Sistema para la gestión de clientes, planes de hosting y dominios.

El proyecto fue desarrollado como parte de una prueba técnica y está compuesto por una API REST desarrollada con Spring Boot y un frontend desarrollado con Next.js.

## 🚀 Tecnologías

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- Jakarta Validation

### Frontend

- Next.js
- React
- TypeScript
- Tailwind CSS
- HeroUI
- Axios

---

## 📁 Proyectos

El sistema está dividido en dos proyectos independientes:

### Backend

API REST desarrollada con Spring Boot.

👉 [Ver repositorio del Backend](AQUI_TU_LINK_BACKEND)

### Frontend

Aplicación web desarrollada con Next.js que consume la API.

👉 [Ver repositorio del Frontend](AQUI_TU_LINK_FRONTEND)

---

# 🔧 Backend

## 📋 Requisitos

Para ejecutar el backend necesitas:

- Java 21 o superior
- Maven

El proyecto utiliza H2 como base de datos relacional en memoria, por lo que no es necesario instalar MySQL, PostgreSQL u otro motor de base de datos.

## ⚙️ Instalación

Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
```

Entrar al proyecto:

```bash
cd hosting-api
```

### Windows

```powershell
.\mvnw.cmd clean install
```

### Linux / macOS

```bash
./mvnw clean install
```

## ▶️ Ejecutar

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

---

# 🗄️ Base de datos

La aplicación utiliza **H2 Database** como base de datos relacional en memoria.

La consola de H2 está disponible en:

```text
http://localhost:8080/h2-console
```

La información almacenada se pierde al detener la aplicación debido a que la base de datos funciona en memoria.

---

# 📚 API REST

La API permite realizar operaciones CRUD sobre:

- Clientes
- Planes de hosting
- Dominios

---

## 👤 Clientes

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/clients` | Obtener todos los clientes |
| GET | `/api/clients/{id}` | Obtener un cliente |
| POST | `/api/clients` | Crear un cliente |
| PUT | `/api/clients/{id}` | Actualizar un cliente |
| DELETE | `/api/clients/{id}` | Eliminar un cliente |

### Crear cliente

```http
POST /api/clients
```

```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "phone": "3001234567"
}
```

---

## 🌐 Planes de Hosting

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/hostingplan` | Obtener todos los planes |
| GET | `/api/hostingplan/{id}` | Obtener un plan |
| POST | `/api/hostingplan` | Crear un plan |
| PUT | `/api/hostingplan/{id}` | Actualizar un plan |
| DELETE | `/api/hostingplan/{id}` | Eliminar un plan |

### Crear plan

```http
POST /api/hostingplan
```

```json
{
  "name": "Premium",
  "price": 50000,
  "status": "Active"
}
```

### Estados disponibles

```text
Active
Inactive
```

---

## 🌎 Dominios

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/domains` | Obtener todos los dominios |
| GET | `/api/domains/{id}` | Obtener un dominio |
| POST | `/api/domains` | Crear un dominio |
| PUT | `/api/domains/{id}` | Actualizar un dominio |
| DELETE | `/api/domains/{id}` | Eliminar un dominio |

### Crear dominio

```http
POST /api/domains
```

```json
{
  "name": "example.com",
  "expiration_date": "2027-08-20",
  "client_id": 1,
  "hosting_planId": 1
}
```

---

# 📌 Reglas de negocio

El backend implementa las reglas de negocio solicitadas en la prueba técnica.

### Fecha de expiración

Un dominio no puede registrarse con una fecha de expiración pasada.

### Estado del plan

Un dominio solamente puede asociarse a un plan de hosting activo.

Los planes disponibles son:

```text
Active
Inactive
```

Si el plan se encuentra en estado `Inactive`, el sistema rechaza la asociación del dominio.

---

# 🔗 Relaciones

La estructura de las entidades es:

```text
Client
   │
   │ 1:N
   ▼
Domain
   │
   │ N:1
   ▼
HostingPlan
```

- Un cliente puede tener múltiples dominios.
- Cada dominio pertenece a un cliente.
- Cada dominio está asociado a un plan de hosting.

---

# 🖥️ Frontend

El frontend fue desarrollado utilizando Next.js y consume los endpoints de la API mediante Axios.

## Tecnologías

- Next.js
- React
- TypeScript
- Tailwind CSS
- HeroUI
- Axios

## Funcionalidades

La interfaz permite gestionar:

### Clientes

- Crear
- Consultar
- Actualizar
- Eliminar

### Planes de hosting

- Crear
- Consultar
- Actualizar
- Eliminar

### Dominios

- Crear
- Consultar
- Actualizar
- Eliminar

Las reglas de negocio relacionadas con los dominios son validadas por el backend.

## Ejecutar el frontend

Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO_FRONTEND>
```

Entrar al proyecto:

```bash
cd hosting-frontend
```

Instalar dependencias:

```bash
npm install
```

Crear el archivo `.env.local`:

```env
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```

Ejecutar:

```bash
npm run dev
```

El frontend estará disponible en:

```text
http://localhost:3000
```

---

# 🔄 Arquitectura

La comunicación entre ambas aplicaciones funciona de la siguiente manera:

```text
┌─────────────────────┐
│      Next.js        │
│      Frontend       │
│    localhost:3000   │
└──────────┬──────────┘
           │
           │ Axios / HTTP
           ▼
┌─────────────────────┐
│     Spring Boot     │
│        REST API     │
│    localhost:8080   │
└──────────┬──────────┘
           │
           │ Spring Data JPA
           ▼
┌─────────────────────┐
│     H2 Database     │
│      In-Memory      │
└─────────────────────┘
```

---

# 🧪 Pruebas

La API fue probada utilizando Postman.

Se realizaron pruebas de:

- CRUD de clientes.
- CRUD de planes de hosting.
- CRUD de dominios.
- Asociación entre clientes y dominios.
- Asociación entre dominios y planes.
- Validación de fechas de expiración.
- Validación de planes de hosting activos.
- Validación de recursos inexistentes.

---

# 📸 Interfaz

El frontend cuenta con interfaces para la gestión de los diferentes recursos del sistema.

Puedes consultar el proyecto frontend aquí:

👉 [Hosting Management Frontend](https://github.com/Ethan7FJ/hosting-frontend)

---

# 👨‍💻 Autor

Desarrollado como parte de una prueba técnica.
