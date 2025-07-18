# 🛍️ Shop API

Shop API es una aplicación backend desarrollada con Spring Boot que gestiona una tienda en línea. Permite registrar usuarios, listar productos, gestionar carritos de compra y procesar órdenes.

---

## 🚀 Tecnologías utilizadas

- **Java 24 + Spring Boot**
- **Gradle** como sistema de construcción
- **Flyway** para migraciones de base de datos
- **SQL Server** como motor de persistencia
- **Lombok** para reducir boilerplate
- **MapStruct** para mapeo entre entidades y DTOs
- **Docker** (opcional) para entorno reproducible
- **Postman** para pruebas de API

---

## 🧱 Entidades principales

- `User`: Registro y autenticación de clientes
- `Product`: Catálogo de productos disponibles
- `Cart`: Carrito de compra por usuario
- `Order`: Órdenes generadas a partir del carrito

---

## ⚙️ Cómo ejecutar el proyecto

### Requisitos

- Java 24
- SQL Server en ejecución
- Gradle instalado (o usar wrapper `./gradlew`)
- Docker (opcional)
