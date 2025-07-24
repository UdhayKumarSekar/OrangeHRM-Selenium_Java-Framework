# OrangeHRM - Selenium Java Automation Framework

A scalable and hybrid test automation framework built for testing the [OrangeHRM](https://opensource-demo.orangehrmlive.com/) application.  
This project follows industry best practices using **Selenium WebDriver, Java, TestNG, Maven**, and **Page Object Model (POM)**. It also supports **CI/CD with Jenkins**, **Docker**, and **Extent Reports** for visualization.

---

## Tech Stack

- **Language:** Java 17+
- **Automation:** Selenium WebDriver
- **Testing Framework:** TestNG
- **Build Tool:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Reporting:** Extent Reports / Allure (optional)
- **CI/CD:** Jenkins Pipeline (Groovy-based)
- **Containerization:** Docker-ready

---

## Key Features

- Cross-browser test execution
- Page Object Model with reusable components
- Test data driven from external files
- Configurable environments using `.properties` files
- Jenkins pipeline integration (with `jenkinsfile`)
- Docker support for containerized execution
- Detailed reporting with screenshots on failure

---

## Project Structure

```bash
OrangeHRM-Selenium_Java-Framework/
├── src/
│   ├── main/java/com/framework/base          # Base classes (DriverManager, Hooks)
│   ├── main/java/com/framework/pages         # Page Object classes
│   ├── main/java/com/framework/utils         # Utility classes (Waits, ConfigReader)
│   └── test/java/com/framework/tests         # Test classes (TestNG)
├── test-output/                              # Extent report output
├── pom.xml                                   # Project dependencies
├── testng.xml                                # TestNG suite file
├── jenkinsfile                               # Jenkins pipeline script
├── .gitignore                                # Ignored files/folders
└── README.md                                 # Project overview


How to Run Tests
1. Clone the Repository
    git clone https://github.com/UdhayKumarSekar/OrangeHRM-Selenium_Java-Framework.git
    cd OrangeHRM-Selenium_Java-Framework

2. Run Tests Using Maven
    mvn clean test

3. Execute via TestNG XML
  Right-click on **testng.xml** → Run as TestNG Suite

Reporting
  Extent Report: Generated under test-output/ directory after execution
  Screenshot on Failure: Enabled in base test class

CI/CD Integration
  Jenkins integrated using **jenkinsfile** (Groovy Script)
  Auto-triggered on push (can be set using GitHub webhook)
  Report publishing and email notifications possible

Docker Support (Optional)
Coming soon! Run your Selenium tests in a containerized Selenium Grid using Docker Compose.

Author
👤 Udhay Kumar Sekar
🔗 [GitHub](https://github.com/UdhayKumarSekar) | 💼 [LinkedIn](https://www.linkedin.com/in/udhaya-kumar-sekar-86198036b/)

📄 License
This project is licensed under the MIT License.
