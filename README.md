# Inventory & Goods Transaction Management REST API

![Java](https://img.shields.io/badge/Java-1.8%2B-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-REST%20API-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-yellow)
![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven)

A robust, enterprise-grade RESTful API built with **Spring Boot** and **PostgreSQL** for managing inventory classification, goods catalog, departmental requisitions, and header-detail inventory transaction workflows.

---

## 📌 Features

- **Inventory Classification & Hierarchy**:
  - Item Categories (`KelompokBarang`) and Sub-Categories (`SubKelompokBarang`).
  - Master Items/Goods Catalog (`Barang`) with pricing, stock tracking, and relational mappings.
- **Departmental Management**:
  - Organizational units / divisions (`Bagian`) receiving or issuing goods.
- **Transaction Processing Engine (Header-Detail Pattern)**:
  - Header transactions (`HeaderTransaksiBarang`) tracking transaction metadata, timestamps, and department associations.
  - Line-item details (`DetailTransaksiBarang`) managing quantity, unit price, and subtotal calculations.
- **Enterprise-Ready Infrastructure**:
  - **Connection Pooling**: HikariCP for high-concurrency database connection management.
  - **Global Exception Handling**: Centralized controller advice (`GlobalExceptionHandler`) delivering standardized JSON responses (`ApiResponse`).
  - **Context Notices & Auditing**: ThreadLocal tracking utilities (`LocalNotices`, `LocalWarnings`) for execution auditing.

---

## 🏗️ Architecture & Tech Stack

- **Backend Framework**: Spring Boot (Spring MVC, Spring Data JPA)
- **Database**: PostgreSQL with Hibernate ORM
- **Connection Pool**: HikariCP
- **Build Tool**: Apache Maven
- **Design Pattern**: Layered Architecture (Controller → Service → Repository → Entity / DTO)

```
src/main/java/springboot/
├── controller/        # REST Controllers exposing JSON endpoints
├── dto/               # Data Transfer Objects for request/response payloads
├── entity/            # JPA Entities mapped to PostgreSQL tables
├── exceptions/        # Custom domain exception classes
├── repositories/      # Spring Data JPA interfaces for database queries
├── service/           # Core business logic and transaction management
└── util/              # Centralized API response format and global handlers
```

---

## 🚀 Getting Started

### 1. Prerequisites
- JDK 8 or JDK 11+ installed
- PostgreSQL 12+ running locally
- Apache Maven

### 2. Database Setup
Create a PostgreSQL database named `erp_tugas`:
```sql
CREATE DATABASE erp_tugas;
```

Update your database credentials in `src/main/resources/application.yml` if different:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/erp_tugas
    username: postgres
    password: YOUR_PASSWORD
```

### 3. Build & Run
```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/springboot-inventory-transaction-api.git
cd springboot-inventory-transaction-api

# Build with Maven
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will start at `http://localhost:8080/SpringBootRestApi`.

---

## 📡 API Endpoints Overview

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/SpringBootRestApi/api/barang/` | Retrieve list of all items |
| `POST` | `/SpringBootRestApi/api/barang/` | Create a new item |
| `GET` | `/SpringBootRestApi/api/barang/{id}` | Get item details by ID |
| `PUT` | `/SpringBootRestApi/api/barang/{id}` | Update item information |
| `DELETE` | `/SpringBootRestApi/api/barang/{id}` | Delete item |
| `GET` | `/SpringBootRestApi/api/headerTransaksiBarang/` | List all inventory transactions |
| `POST` | `/SpringBootRestApi/api/headerTransaksiBarang/` | Create a new transaction with line items |
| `GET` | `/SpringBootRestApi/api/bagian/` | List all company departments |
| `GET` | `/SpringBootRestApi/api/subKelompokBarang/` | List all item sub-categories |

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
