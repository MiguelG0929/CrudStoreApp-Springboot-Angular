# CrudStoreApp-Springboot-Angular
Simulación de E-commerce profesional con sistema de (inventarios,usuarios,seguridad)

📦 CrudStore Backend
📖 Descripción

CrudStore Backend es una API REST desarrollada con Spring Boot que simula el backend de una tienda online.
Permite la gestión de categorías y productos, implementando operaciones CRUD completas con persistencia en PostgreSQL, validaciones, desactivación lógica y una arquitectura limpia basada en buenas prácticas.

El backend está diseñado para ser consumido por un frontend Angular (documentado por separado).

🛠️ Stack Tecnológico
Tecnología	Versión	Uso
Java	17	Lenguaje base
Spring Boot	4.0.2	Framework principal
Spring Web MVC	—	API REST
Spring Data JPA	—	Persistencia
Hibernate	—	ORM
PostgreSQL	—	Base de datos
Maven	—	Gestión de dependencias
Lombok	—	Reducción de boilerplate
Jakarta Validation	—	Validación de datos
🏗️ Arquitectura

El proyecto sigue una arquitectura en capas (Layered Architecture), separando responsabilidades claramente:

Controller → Service → Repository → Database

Capas

Controller

Exposición de endpoints REST

Manejo de HTTP status codes

Validación de datos de entrada (@Valid)

Service

Lógica de negocio

Reglas de dominio

Transacciones (@Transactional)

Repository

Acceso a datos mediante Spring Data JPA

Model / Entity

Representación del modelo de dominio

DTO

Separación entre modelo interno y contratos API

📂 Estructura del Proyecto
src/main/java/com/mglopez.crudstore
├── config
│   ├── CorsConfig
│   └── DataInitializer
├── controllers
│   ├── CategoriaController
│   └── ProductoController
├── dtos
│   ├── categoria
│   └── producto
├── models
│   ├── CategoriaEntity
│   └── ProductoEntity
├── repositories
│   ├── CategoriaRepository
│   └── ProductoRepository
├── services
│   ├── CategoriaService
│   ├── ProductoService
│   └── impl
│       ├── CategoriaServiceImpl
│       └── ProductoServiceImpl

🧩 Patrones de Diseño Aplicados

DTO Pattern

CreateDTO para entrada

ResponseDTO para salida

Repository Pattern

Service Layer Pattern

Builder Pattern

Uso de @Builder en entidades

Dependency Injection

Inyección por constructor (best practice)

Soft Delete (Eliminación lógica)

Campos activo / activa

🗄️ Modelo de Datos
Entidades principales
CategoriaEntity

Relación OneToMany con Producto

Desactivación lógica

Restricción de nombre único

ProductoEntity

Relación ManyToOne con Categoria

Precio con precisión decimal

Eliminación lógica

📌 Diagrama Entidad–Relación



🔐 Validaciones

Se utilizan Jakarta Validation:

@NotBlank

@NotNull

@Positive

@Size

Ejemplo:

@NotNull
@Positive
BigDecimal precio


Esto asegura integridad de datos desde la capa API.

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


⚠️ En producción se recomienda usar:

ddl-auto=validate

🌐 CORS

Configurado para permitir acceso desde Angular:

.allowedOrigins("http://localhost:4200")
.allowedMethods("GET", "POST", "PUT", "DELETE")

▶️ Ejecución del Proyecto
mvn clean install
mvn spring-boot:run


Servidor:

http://localhost:9525

📡 Endpoints Principales
Categorías
Método	Endpoint	Descripción
POST	/api/categorias	Crear categoría
GET	/api/categorias	Listar activas
GET	/api/categorias/{id}	Obtener por ID
PUT	/api/categorias/{id}	Actualizar
DELETE	/api/categorias/{id}	Eliminación lógica
Productos
Método	Endpoint	Descripción
POST	/api/productos	Crear producto
GET	/api/productos	Listar activos
GET	/api/productos/{id}	Obtener por ID
GET	/api/productos/categoria/{id}	Por categoría
PUT	/api/productos/{id}	Actualizar
DELETE	/api/productos/{id}	Eliminación lógica
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
