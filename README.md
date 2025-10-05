
# 📚 Library Management System

A comprehensive Library Management System built with **Java** and **Spring Boot**. This application allows users to manage books, members, loans, and generate reports through a modern web interface. The system features a modern, responsive login page with external CSS and JavaScript for enhanced user experience.

---

## 🚀 Skills & Technologies Used

- **Java** ☕
- **Spring Boot** 🌱
- **Spring Data JPA** 🗄️
- **Spring Security** 🔒
- **Thymeleaf** (template engine) 📝
- **Maven** ⚙️
- **MySQL** (database) 🐬
- **Docker** 🐳
- **RESTful API** 🌐
- **HTML5 & CSS3** 🎨
- **Bootstrap** (responsive UI) 🅱️
- **JavaScript (ES6+)** ✨
- **AJAX (fetch API)** 🔄
- **Chart.js** 📊
- **JUnit** (testing) 🧪
- **JWT (JSON Web Token)** 🔑
- **Validation (Spring/Bean)** ✅
- **Error Handling** 🚨
- **Responsive Design** 📱
- **Render** (cloud deployment) ☁️

---

## ✨ Features
- Add, edit, and delete books
- Manage library members
- Track book loans and returns
- Dashboard with charts and statistics
- Generate reports
- Responsive login page with external CSS/JS
- Secure authentication & authorization
- Error handling with custom error pages
- Dockerized deployment


---

src/
## 🗂️ Project Structure
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
         static/
            css/
               dashboard.css
               error.css
               login.css     # Login page styles
            js/
               chart-ajax.js
               home.js
               login.js      # Login page scripts
            icon/
               title.png
         templates/
            add-book.html
            add-member.html
            book.html
            dashboard.html
            edit-book.html
            edit-member.html
            loans.html
            members.html
            reports.html
            error.html      # Custom error page
            layout/
               index.html
            login.html      # Responsive login page
         application.properties
   test/
      java/pranshu/library/management/
         ApplicationTests.java
Dockerfile
pom.xml
README.md
```

```

---

## 🛠️ Prerequisites
- Java 17+
- Maven
- MySQL (or compatible database)


---

## 🏃‍♂️ Getting Started

1. **Clone the repository:**
   ```sh
   git clone https://github.com/pranshu-oz/Library-Management.git
   ```
2. **Navigate to the project directory:**
   ```sh
   cd library.management
   ```
3. **Configure your database** in `src/main/resources/application.properties` (default: MySQL)
4. **Build the project:**
   ```sh
   mvn clean install
   ```
5. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```
6. **Access the web interface:** [http://localhost:8999](http://localhost:8999)

### 🐳 Docker
To build and run with Docker:
```sh
docker build -t library-management .
docker run -p 8080:8080 library-management
```

## **Code Live On** [https://library-management-gal7.onrender.com/login](https://library-management-gal7.onrender.com/login)
---

## 🎨 UI Highlights
- Modern, responsive login page styled with Bootstrap and custom CSS (`login.css`)
- Interactive login error feedback powered by JavaScript (`login.js`)
- Dashboard with dynamic charts (Chart.js)
- Custom error page (`error.html` + `error.css`)


---

## 🤝 Contributing
Contributions are welcome! Please fork the repository and submit a pull request.


---

## 📄 License
This project is licensed under the MIT License.
