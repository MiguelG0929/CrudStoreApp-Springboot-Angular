# 📦 CrudStore Backend

## 📖 Simulación de E-commerce profesional
**CrudStore Backend** es una API REST desarrollada con **Spring Boot** que simula el backend de una tienda online.  
Permite la gestión de **categorías** y **productos**, implementando operaciones CRUD completas con **persistencia en PostgreSQL**, validaciones, desactivación lógica y una arquitectura limpia basada en buenas prácticas.  

El backend está diseñado para ser consumido por un **frontend Angular** (documentado por separado).

---

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Uso |
|-----------|--------|-----|
| Java | 17 | Lenguaje base |
| Spring Boot | 4.0.2 | Framework principal |
| Spring Web MVC | — | API REST |
| Spring Data JPA | — | Persistencia |
| Hibernate | — | ORM |
| PostgreSQL | — | Base de datos |
| Maven | — | Gestión de dependencias |
| Lombok | — | Reducción de boilerplate |
| Jakarta Validation | — | Validación de datos |

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura en capas (Layered Architecture)**:


### Capas

**Controller**  
- Exposición de endpoints REST  
- Manejo de HTTP status codes  
- Validación de datos de entrada (`@Valid`)  

**Service**  
- Lógica de negocio  
- Reglas de dominio  
- Transacciones (`@Transactional`)  

**Repository**  
- Acceso a datos mediante Spring Data JPA  

**Model / Entity**  
- Representación del modelo de dominio  

**DTO**  
- Separación entre modelo interno y contratos API  

---

## 📂 Estructura del Proyecto

![Estructura de directorios del proyecto](docs/organizacion-carpetas.png)

---

## 🧩 Patrones de Diseño Aplicados

- **DTO Pattern**: `CreateDTO` para entrada / `ResponseDTO` para salida  
- **Repository Pattern**  
- **Service Layer Pattern**  
- **Builder Pattern** (`@Builder` en entidades)  
- **Dependency Injection**: inyección por constructor (mejor práctica)  
- **Soft Delete (Eliminación lógica)**: campos `activo` / `activa`  

---

## 🗄️ Modelo de Datos

### CategoriaEntity
- Relación **OneToMany** con `ProductoEntity`  
- Desactivación lógica (`activa`)  
- Nombre único  

### ProductoEntity
- Relación **ManyToOne** con `CategoriaEntity`  
- Precio con precisión decimal (`BigDecimal`)  
- Eliminación lógica (`activo`)  

📌 **Diagrama Entidad–Relación**

![Diagrama ERD de la base de datos](docs/entidad-relacion.png)

---

## 🔐 Validaciones

Se utilizan **Jakarta Validation**:

- `@NotBlank`  
- `@NotNull`  
- `@Positive`  
- `@Size`  

Ejemplo en Producto:

java
@NotNull
@Positive
BigDecimal precio;

⚙️ Configuración
Base de Datos
spring.datasource.url=jdbc:postgresql://localhost:5432/crudstore_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

JPA / Hibernate
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
⚠️ En producción se recomienda usar: ddl-auto=validate

🌐 CORS
Configurado para permitir acceso desde Angular:
.allowedOrigins("http://localhost:4200")
.allowedMethods("GET", "POST", "PUT", "DELETE")

▶️ Ejecución del Proyecto
mvn clean install
mvn spring-boot:run
Servidor: http://localhost:9525

📡 Endpoints Principales
| Método | Endpoint             | Descripción        |
| ------ | -------------------- | ------------------ |
| POST   | /api/categorias      | Crear categoría    |
| GET    | /api/categorias      | Listar activas     |
| GET    | /api/categorias/{id} | Obtener por ID     |
| PUT    | /api/categorias/{id} | Actualizar         |
| DELETE | /api/categorias/{id} | Eliminación lógica |

| Método | Endpoint                      | Descripción        |
| ------ | ----------------------------- | ------------------ |
| POST   | /api/productos                | Crear producto     |
| GET    | /api/productos                | Listar activos     |
| GET    | /api/productos/{id}           | Obtener por ID     |
| GET    | /api/productos/categoria/{id} | Por categoría      |
| PUT    | /api/productos/{id}           | Actualizar         |
| DELETE | /api/productos/{id}           | Eliminación lógica |

🧪 Inicialización de Datos

Se utiliza CommandLineRunner para cargar datos de prueba al iniciar la aplicación:

Categorías iniciales

Productos asociados

Ideal para entorno de desarrollo.

🚀 Buenas Prácticas Implementadas

✅ Separación de capas
✅ DTOs para evitar exponer entidades
✅ Eliminación lógica
✅ Inyección por constructor
✅ Validaciones centralizadas
✅ Uso de transacciones
✅ Código limpio y legible



