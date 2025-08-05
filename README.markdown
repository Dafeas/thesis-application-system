# Thesis Application Management System

## Overview
This Spring Boot web application, developed for my [Degree, e.g., B.Sc. in Computer Science] at [University Name] in [Year], streamlines the diploma thesis application process. Students can register, browse and apply for thesis subjects, and manage profiles, while professors can create/delete subjects, review applications, and assign theses. The system uses Spring Security for role-based authentication, Spring Data JPA for database operations, and Thymeleaf for rendering views.

## Features
- User registration and login with Student and Professor roles.
- Student dashboard: Browse subjects, apply for subjects, update profile (year of studies, grades).
- Professor dashboard: Manage subjects, view student applications, assign theses.
- Secure authentication with BCrypt and role-based URL restrictions.
- Persistent storage of users, profiles, subjects, applications, and theses.

## Technologies Used
- Java 17
- Spring Boot, Spring Security, Spring Data JPA
- Thymeleaf, Bootstrap 5
- MySQL (or H2 for development)
- Maven, SLF4J

## Prerequisites
- JDK 17
- Maven 3.8+
- MySQL 8.0 (or H2)
- Clone the repository:
  ```bash
  git clone https://github.com/[your-username]/thesis-application-system.git
  ```

## Installation
1. Create a MySQL database (e.g., `thesis_db`).
2. Configure `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/thesis_db?useSSL=false
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.thymeleaf.cache=false
   logging.level.org.springframework=INFO
   ```
3. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Access at `http://localhost:8080`.

## Usage
- Register at `/register` as a Student or Professor.
- Log in at `/login` to access role-specific dashboards.
- **Students**: Browse subjects at `/student/subjects`, apply via `/student/subject-details/{id}`.
- **Professors**: Add subjects at `/professor/showAddForm`, assign theses at `/professor/applications`.

## Database Schema
See `docs/schema.sql` for details:
- `users`: User credentials and roles.
- `student`, `professor`: Profile details.
- `subject`: Thesis subjects.
- `application`: Student applications.
- `thesis`: Assigned theses with unique student-subject pairs.

## Thesis Document
The thesis is in `docs/thesis.pdf` [to be added]. Abstract:
> This thesis presents a web-based system for managing diploma thesis applications, built with Spring Boot, Spring Security, and Spring Data JPA. It optimizes the thesis assignment process with secure, role-based workflows for students and professors.

## License
MIT License. See `LICENSE` for details.

