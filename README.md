# Library Management System

A comprehensive Library Management System built with Java and Spring Boot. This application allows users to manage books, members, loans, and generate reports through a modern web interface.

## Features
- Add, edit, and delete books
- Manage library members
- Track book loans and returns
- Dashboard with charts and statistics
- Generate reports

## Technologies Used
- Java
- Spring Boot
- Maven
- Thymeleaf (for HTML templates)
- JavaScript (AJAX for charts)
- CSS

## Project Structure
```
src/
  main/
    java/pranshu/library/management/
      controller/      # REST and web controllers
      model/           # Entity classes
      repository/      # Spring Data repositories
      service/         # Business logic
      Application.java # Main entry point
    resources/
      static/          # Static assets (CSS, JS, images)
      templates/       # Thymeleaf HTML templates
      application.properties
  test/
    java/pranshu/library/management/
      ApplicationTests.java
```

## Getting Started
### Prerequisites
- Java 8+
- Maven

### Build and Run
1. Clone the repository:
   ```sh
   git clone https://github.com/pranshu-oz/Library-Management.git
   ```
2. Navigate to the project directory:
   ```sh
   cd library.management
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```
5. Access the web interface at [http://localhost:8080](http://localhost:8080)

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.
