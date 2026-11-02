# Task Tracker REST API

A Task Tracking RESTful service developed with Java 17 and Spring Boot 3. The service exposes CRUD operations for task lifecycles, using MapStruct for decoupled DTO-to-entity mappings and Spring Data JPA / Hibernate connected to MySQL.

---

## 🚀 Tech Stack

- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate
- **Database:** MySQL / MariaDB
- **Data Mapping & Boilerplate:** MapStruct, Lombok
- **Build Tool:** Apache Maven
- **Testing & API Tooling:** Postman, cURL

---

## 🏗 Architecture Highlights

- **Layered Architecture:** Strict separation between Controller, Service, Repository, Entity, and DTO layers.
- **DTO Decoupling & MapStruct:** Request and Response models isolate internal database entities from external client payloads; MapStruct handles compile-time object mapping with a null-safe property update strategy.
- **Response Management:** Endpoints utilize Spring's `ResponseEntity` to return appropriate HTTP status codes (`200 OK`, `201 Created`, `204 No Content`).
- **Persistence Layer:** Spring Data JPA repository pattern handles CRUD operations and status-based query filtering over MySQL with UTC timestamp support.

---

## 🛠 Getting Started

### Prerequisites

- JDK 17+
- MySQL or MariaDB running on port `3306`

### Database Setup

Log into MySQL/MariaDB and execute:

```sql
CREATE DATABASE IF NOT EXISTS tasks;
CREATE USER IF NOT EXISTS 'springuser'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON tasks.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;