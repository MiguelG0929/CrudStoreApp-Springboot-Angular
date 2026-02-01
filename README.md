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

```java
@NotNull
@Positive
BigDecimal precio;




