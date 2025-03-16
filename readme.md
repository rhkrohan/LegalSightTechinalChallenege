# Speech Manager Project

This project is a simple **Speech Manager** application built using **Java** and **Spring Boot**. It provides basic functionality to manage speeches, including adding, retrieving, and updating speeches. The project is designed with a focus on **clean code**, **unit testing**, and **mutation testing** to ensure high-quality software.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Setup and Installation](#setup-and-installation)
5. [Running the Application](#running-the-application)
6. [API Endpoints](#api-endpoints)
7. [Testing](#testing)
   - [Unit Tests](#unit-tests)
8. [Code Coverage](#code-coverage)
9. [Contributing](#contributing)
10. [License](#license)
11. [Acknowledgments](#acknowledgments)

---

## Project Overview

The **Speech Manager** project is a backend application that allows users to manage speeches. It provides RESTful API endpoints to perform CRUD operations on speech entities, stored in an in-memory database by default. The project is built using **Spring Boot** for the backend, **Spring Data JPA** for database interactions, and includes a comprehensive test suite to ensure reliability and robustness.

The codebase follows **Test-Driven Development (TDD)** principles, with unit tests written for all components and mutation testing to validate the effectiveness of the test suite.

---

## Features

- **Retrieve All Speeches**: Fetch a list of all speeches stored in the database.
- **Add a New Speech**: Add a new speech with details such as text, author, keywords, and speech date.
- **Update an Existing Speech**: Update the details of an existing speech by providing its ID and updated fields.
- **Exception Handling**: Proper handling of errors, such as invalid speech IDs or missing resources.
- **Unit Testing**: Comprehensive unit tests for all layers (entity, service, controller).

---

## Technologies Used

- **Java**: Version 11 or higher, the primary programming language.
- **Spring Boot**: Framework for building the RESTful backend application.
- **Spring Data JPA**: Simplifies database interactions with an H2 in-memory database.
- **H2 Database**: In-memory database for development and testing.
- **Mockito**: Mocking framework for isolating dependencies in unit tests.
- **JUnit**: Testing framework for writing and running unit tests.
- **PITest**: Mutation testing tool to ensure 100% mutation coverage.
- **Maven**: Build automation tool for dependency management and test execution.

---

## Setup and Installation

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher.
- **Apache Maven**: Version 3.6 or higher.
- **IDE**: Recommended (e.g., IntelliJ IDEA, Eclipse, VS Code with Java extensions).
- **Git**: For cloning the repository.

### Steps to Set Up the Project

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/rhkrohan/LegalSightTechinalChallenege/
   cd speech-manager
   ```

2. **Install Dependencies**:
   Run the following command to download all required dependencies:

   ```bash
   mvn clean install
   ```

3. **Database Configuration**:
   - The project uses an in-memory **H2 database** by default, requiring no additional setup.
   - To use a persistent database (e.g., MySQL, PostgreSQL), update `src/main/resources/application.properties` with the appropriate configuration:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/speechdb
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```

---

## Running the Application

To start the application, run:

```bash
mvn spring-boot:run
```

- The application will launch on **port 8080**.
- Access the API endpoints using tools like **Postman**, **cURL**, or a web browser.
- The H2 console is available at `http://localhost:8080/h2-console` (default credentials: `sa` with no password).

---

## API Endpoints

The application exposes the following RESTful endpoints under the base path `/api/speeches`:

| Method | Endpoint             | Description               | Request Body Example                                                          | Response Example                                |
| ------ | -------------------- | ------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------- |
| GET    | `/api/speeches`      | Retrieve all speeches     | N/A                                                                           | `[{"id":1,"text":"Hello","author":"Alice"...}]` |
| POST   | `/api/speeches`      | Add a new speech          | `{"text":"Hello","author":"Alice","keywords":"hi","speechDate":"2025-03-15"}` | `{"id":1,"text":"Hello"...}`                    |
| PUT    | `/api/speeches/{id}` | Update an existing speech | `{"text":"Updated","author":"Bob","keywords":"up","speechDate":"2025-03-15"}` | `{"id":1,"text":"Updated"...}`                  |

- **Status Codes**:
  - `200 OK`: Successful GET or PUT.
  - `201 Created`: Successful POST.
  - `404 Not Found`: Speech ID not found during update.
  - `400 Bad Request`: Invalid request body.

---

## Testing

### Unit Tests

The project includes a comprehensive test suite covering all layers:

- **`Speech`**: Tests constructors, getters, setters, and edge cases (nulls, empty strings).
- **`SpeechService`**: Tests business logic for retrieving, adding, and updating speeches.
- **`SpeechController`**: Tests REST endpoints, HTTP responses, and exception handling.
- **`SpeechRepository`**: Integration tests with H2 database.
- **`SpeechManagementApplication`**: Ensures the Spring context loads.

Run the unit tests with:

```bash
mvn test
```

## License

This project is licensed under the **MIT License**.

---

## Acknowledgments

- **Spring Boot**: For providing a robust framework for building Java applications.
- **Mockito**: For simplifying unit testing with mocking.
- **PITest**: For enabling mutation testing to ensure code quality.
- **H2 Database**: For providing an easy-to-use in-memory database for development and testing.

---

Thank you for checking out the **Speech Manager** project! If you have any questions, feedback, or issues, feel free to open an issue or contact me directly at [mk3972@drexel.edu](mailto:mk3972@drexel.edu). 😊

---
