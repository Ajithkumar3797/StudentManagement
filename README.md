Student Management API
Spring Boot REST API — Documentation
Overview
The Student Management API is a RESTful web service built with Spring Boot that provides full CRUD operations for managing student records. It uses MySQL as the database backend with Spring Data JPA for persistence.
Tech Stack
Technology	Version	Purpose
Spring Boot	4.0.5	Application framework
Spring Data JPA	—	Database ORM
MySQL	—	Relational database
Lombok	—	Boilerplate reduction
Java	17	Runtime
Maven	—	Build tool

Project Structure
src/main/java/com/example/student/
  ├── StudentApplication.java          # Main entry point
  ├── controller/
  │   └── StudentsController.java       # REST endpoints
  ├── model/
  │   └── Students.java                 # JPA entity
  ├── repository/
  │   └── StudentRepository.java        # JPA repository
  ├── service/
  │   └── StudentsService.java          # Service interface
  └── serviceImpl/
      └── StudentsServiceImpl.java      # Service implementation

Data Model — Students
Field	Type	Constraint	Description
id	Long	PK, Auto-increment	Unique student identifier
name	String	—	Full name of the student
email	String	—	Email address
course	String	—	Enrolled course

API Endpoints
Base URL: http://localhost:8081/students

Method	Endpoint	Description	Request Body
POST	/students	Create a student	JSON: name, email, course
GET	/students	Get all students	—
GET	/students/{id}	Get student by ID	—
PUT	/students/{id}	Update a student	JSON: name, email, course
DELETE	/students/{id}	Delete a student	—

Request & Response Examples
Create Student — POST /students
Request Body:
{
"name":   "John Doe",
"email":  "john@example.com",
"course": "Computer Science"
}
Response (200 OK):
{
"id":     1,
"name":   "John Doe",
"email":  "john@example.com",
"course": "Computer Science"
}
Update Student — PUT /students/{id}
Request Body (same structure as POST):
{
"name":   "Jane Doe",
"email":  "jane@example.com",
"course": "Data Science"
}
Delete Student — DELETE /students/{id}
Returns 200 OK with no response body.
Configuration
application.properties:
spring.datasource.url=jdbc:mysql://192.168.10.13/studentdb
spring.datasource.username=test
spring.datasource.password=<your-password>
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
server.port=8081

Note: Never commit actual credentials to source control. Use environment variables or a secrets manager in production.
Prerequisites
•	Java 17 or higher
•	Maven 3.6+
•	MySQL running and accessible at the configured host
•	Database studentdb created in MySQL

Setup & Run
1. Create the database
CREATE DATABASE studentdb;
2. Configure credentials
Update src/main/resources/application.properties with your MySQL host, username, and password.
3. Build
mvn clean install
4. Run
mvn spring-boot:run
The API will start on http://localhost:8081
5. Test with curl
curl -X POST http://localhost:8081/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com","course":"AI"}'

Notes
•	JPA is configured with show-sql=true — SQL queries are visible in the console during development.
•	The database schema (table creation) is managed automatically by Hibernate based on the Students entity.
•	getStudentById and updateStudent return null when the ID is not found — consider returning 404 responses in production.
