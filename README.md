# KATA_API_BDD_JAVA
Framework is build with Rest assured BDD Cucumber

1. Features
This is a BDD (Behavior-Driven Development) test automation framework using Cucumber + RestAssured + Java for testing a Hotel Booking website via API's.
It supports creating, retrieving, and deleting bookings using your specified payload schema.

Cucumber (BDD) → Human-readable test cases
RestAssured → API request/response handling
JSON Schema Validation → Ensure response contracts

2. Summary
This project is built to support End to End API test flows as mentioned below: Creation of hotel booking via JSON payload (POST) GET booking details by Booking ID and verify response fields. DELETE booking and verify status. Assertions on HTTP status codes, JSON response content, and request–response match. Automatic cleanup to maintain idempotent tests.
Also this project is integrated with Code metrics plugin which helps is self evaluating the code on the fly saving review time and helps in code readability 

4. Prerequisites
Java 17 - programming language Maven 4.0.0 - build tool Rest Assured 5.5.2 - RESTful API testing library Cucumber 7.22.2 - BDD framework JSON Schema Validator 5.5.5 - JSON validation library JUnit Jupiter 5.12.2 - Test library JUnit Platform Suite 1.12.2 - Test discovery and test execution infrastructure Lombok 1.18.38 - Library to reduce boiler plate and increase code readability GSON 2.11.0 - JSON parsing library

Accessible Booking API endpoint Website URL - https://automationintesting.online/

4. Setup
Clone or copy this project into your local machine. Open in your IDE (e.g. IntelliJ IDEA, Eclipse). Update maven dependency (if needed) via pom.xml. Configure the API base URI (in ConfigManager.java) to point to your running booking API.

5. Configuration
Set BASE_URI to the host/URL of your booking API (https://automationintesting.online/) under (config.properties). If any changes to your API path (eg: /bookings instead of /booking), update the endpoints in step definitions (Hooks.java). You can also externalize configuration (e.g. via application.properties, environment variables) for flexibility in CI environments.

6. Running Tests

Via Maven:
mvn clean test - Has some issues with Junit tests debugging it.
This will run the Cucumber tests (via RunCucumberTest) and generate the default report.
Via IDE: Run the RunCucumberTest class as a JUnit test. Cucumber will execute scenarios and produce a report.

7. Extending the Framework
Add negative / edge-case scenarios in .feature files (e.g. missing fields, invalid dates).

Use Scenario Outline with examples to test multiple datasets.

Integrate JSON Schema validation (using libraries like json-schema-validator) for structural checks.

Extract common logic into helper methods or utility classes.

Support authentication, headers, parameters, and other API concerns via step definitions.

Use different configurations (dev, staging, prod) via environment variables or Maven profiles.

8. Cleanup & Test Isolation
The @After hook in Hooks.java deletes any bookings created during a scenario, making the tests repeatable and independent.

Adjust the delete logic or expected status code if your API uses a different convention (e.g. 204 No Content or 200 OK).

9. Reporting
Cucumber’s HTML report (configured in RunCucumberTest) is generated under target/cucumber-reports.html.

You can integrate advanced reporting tools like Allure, ExtentReports, or others for richer insights.

10. Troubleshooting & Tips
Mapping errors: Ensure your POJOs match the JSON response field names (case sensitivity, types).

Null values: Check that a booking is successfully created before executing GET or DELETE steps.

Status mismatch: Confirm the expected HTTP status code (API may return 200, 204, or 201 for deletion).

Connectivity issues: Ensure the API server is running and reachable from your machine.

Parallel execution: If running tests in parallel, guarantee unique booking IDs or isolate data to avoid collisions.

11. Known Issues

Auth Swagger - https://automationintesting.online/auth/swagger-ui/index.html (currently not working - 404 error) 
Booking Swagger - https://automationintesting.online/booking/swagger-ui/index.html (currently not working - 404 error)

Since the swagger UI is down delete and update requests are parked aside as of now, once resolved corresponding validations and verifications will be integrated

12. Current Test Coverage

Have Covered positive and negative POST requests using https://automationintesting.online/api/booking
Test run can be viewed under cucumber html report which can be accessed under targets/ folder
PUT/DELETE/GET framework level coverage is there have deferred the feature files due to unavailability of Swagger UI 
