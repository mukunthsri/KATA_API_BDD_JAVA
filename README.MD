# 🏨 KATA_API_BDD_JAVA

**A BDD-based API testing framework for hotel booking services using Cucumber, RestAssured, and Java**

---

## 📌 Overview

`KATA_API_BDD_JAVA` is a Behavior-Driven Development (BDD) test automation framework designed to validate the API endpoints of a hotel booking system. Built with Cucumber, RestAssured, and Java, it supports:

- **Creating** hotel bookings via JSON payload (POST)
- **Retrieving** booking details by Booking ID (GET)
- **Deleting** bookings and verifying status (DELETE)
- **JSON Schema Validation** to ensure response contracts

---

## 🛠️ Tech Stack

- **Java**: Version 17
- **Build Tool**: Maven 4.0.0
- **API Testing**: RestAssured 5.5.2
- **BDD Framework**: Cucumber 7.22.2
- **Test Library**: JUnit Jupiter 5.12.2
- **JSON Schema Validator**: 5.5.5
- **Code Quality**: JUnit Platform Suite 1.12.2
- **Code Readability**: Lombok 1.18.38
- **JSON Parsing**: GSON 2.11.0

---

## 🚀 Features

- **BDD Scenarios**: Human-readable test cases using Cucumber.
- **API Interactions**: Seamless request/response handling with RestAssured.
- **JSON Schema Validation**: Ensures response contracts are met.
- **Test Isolation**: Automatic cleanup of test data to maintain idempotent tests.
- **Code Quality Integration**: Integrated with code metrics plugin for on-the-fly code evaluation.

---

## 🔗 API Endpoint

- **Base URL**: [https://automationintesting.online/](https://automationintesting.online/)

---

## ⚙️ Setup & Configuration

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/KATA_API_BDD_JAVA.git
   cd KATA_API_BDD_JAVA

Open in IDE: Use IntelliJ IDEA, Eclipse, or your preferred Java IDE.

Update Maven Dependencies: Ensure all dependencies are up to date via pom.xml.

Configure API Base URI:

Locate ConfigManager.java.

Set BASE_URI to the host/URL of your booking API:

public static final String BASE_URI = "https://automationintesting.online/";


Configure Endpoints:

Update API paths in Hooks.java if necessary (e.g., /bookings instead of /booking).

🏃 Running Tests

Via Maven:

mvn clean test


Note: Debugging JUnit tests may require additional configuration.

Via IDE:

Run the RunCucumberTest class as a JUnit test.

Cucumber will execute scenarios and produce a report.

🧪 Test Coverage

Positive & Negative POST Requests: Validated via https://automationintesting.online/api/booking

GET/PUT/DELETE: Framework-level coverage; feature files deferred due to Swagger UI unavailability.

🧹 Cleanup & Test Isolation

The @After hook in Hooks.java deletes any bookings created during a scenario, ensuring repeatable and independent tests.

Adjust the delete logic or expected status code if your API uses a different convention (e.g., 204 No Content or 200 OK).

📊 Reporting

Cucumber HTML Report: Generated under target/cucumber-reports.html.

Advanced Reporting: Integrate tools like Allure or ExtentReports for richer insights.

🛠️ Extending the Framework

Negative & Edge-Case Scenarios: Add scenarios in .feature files (e.g., missing fields, invalid dates).

Scenario Outline: Use with examples to test multiple datasets.

JSON Schema Validation: Integrate using libraries like json-schema-validator.

Helper Methods: Extract common logic into utility classes.

API Concerns: Support authentication, headers, parameters via step definitions.

Environment Configurations: Use environment variables or Maven profiles for different configurations (dev, staging, prod).

🐞 Troubleshooting & Tips

Mapping Errors: Ensure POJOs match JSON response field names (case sensitivity, types).

Null Values: Verify that a booking is successfully created before executing GET or DELETE steps.

Status Mismatch: Confirm the expected HTTP status code (API may return 200, 204, or 201 for deletion).

Connectivity Issues: Ensure the API server is running and reachable from your machine.

Parallel Execution: Guarantee unique booking IDs or isolate data to avoid collisions.

⚠️ Known Issues

Auth Swagger UI: https://automationintesting.online/auth/swagger-ui/index.html
 – Currently not working (404 error).

Booking Swagger UI: https://automationintesting.online/booking/swagger-ui/index.html
 – Currently not working (404 error).

Note: Due to the unavailability of Swagger UI, DELETE and PUT requests are currently parked. These validations and verifications will be integrated once resolved.
