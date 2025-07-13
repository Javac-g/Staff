
<div id="header">
  <img src="https://github.com/Javac-g/Staff/blob/main/SMS.png?raw=true"/>
</div>

# Staff Management System

A Java-based web application designed to manage staff records efficiently. This project includes functionalities for handling employee data, roles, and departmental information through a user-friendly interface.

## Features

- Add, edit, update, and delete employee records - basic CRUD operations.
- Assign roles and departments to employees
- Search and filter employee information
- Secure authentication system
- Logging of user activities

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- PostgreSQL
- HTML, CSS, JavaScript

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven build tool
- PostgreSQL server
- An IDE like IntelliJ IDEA or Eclipse

### Database Setup

1. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE staff_db;
2. Configure database credentials in application.properties or application.yml:
 - spring.datasource.url=jdbc:postgresql://localhost:5432/staff_db
 - spring.datasource.username=your_username
 - spring.datasource.password=your_password
 - spring.jpa.hibernate.ddl-auto=update

3. Installation
  Clone the repository:
  git clone https://github.com/Javac-g/Staff.git
  Navigate to the project directory:

4. Build the project using Maven:
  mvn clean install

5. Run the application:
  mvn spring-boot:run

6. Once the application is running, access it via your web browser at:
 - http://localhost:8080

7. Contributing
 - Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

8. License
This project is licensed under the MIT License. See the LICENSE file for details.










