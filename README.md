# Employee Management System 
A complete console-based Employee Management System built using **Core Java 17** and **MySQL (JDBC)** — without any frameworks.  
Implements full CRUD operations, input validation, DAO pattern, and a clean interactive menu.

---

## 📌 Features
- Add new employee  
- View all employees (formatted table)  
- Search employee by ID  
- Update employee information  
- Delete employee by ID  
- Console-based menu that loops until Exit  
- Safe input handling and validation  
- Uses Java 17 `record` for the Employee model  
- All database operations use **PreparedStatement + JDBC**  
- Implements DAO & Singleton design patterns  

---

## 🛠️ Tech Stack
- **Java 17**
- **MySQL Server 9.5.0**  
  Download: https://cdn.mysql.com//Downloads/MySQL-9.5/mysql-9.5.0-winx64.msi
- **MySQL JDBC Driver (mysql-connector-j 9.5.0)**  
  Download: https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-j-9.5.0.zip
- No frameworks (No Spring, No Hibernate, No Jakarta)

---

## 📂 Project Structure
<img width="943" height="477" alt="6" src="https://github.com/user-attachments/assets/8d016b72-cc4d-43f5-b180-71fe91b8d0a6" />


---

## 🗄️ MySQL Database Setup

Run the following SQL script to create the database and table:

```sql
CREATE DATABASE company_db;
USE company_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50),
    salary DOUBLE NOT NULL,
    joining_date DATE DEFAULT (CURRENT_DATE)
);



