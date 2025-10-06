@Positive @ValidCredentials
Feature: Booking API Operations
  As an user
  I want to perform booking operations
  So that I can manage hotel bookings

  Background:
    Given the base URI is set to "https://automationintesting.online/"

  @CreateBooking
  Scenario: Create a new booking with valid data
    When I create a booking with following details:
      | firstname | KataApi           |
      | lastname  | Automation        |
      | email     | apikata@gmail.com |
      | phone     | +23423252623      |
      | checkin   | 2025-10-20        |
      | checkout  | 2025-10-21        |
      | deposit   | false             |
      | roomid    | 2                 |
    Then the response status code should be 201
    And the response should contain booking details
    And the booking ID should be generated

  @GetBooking
  Scenario: Get booking by ID
    Given a booking exists with ID
    When I retrieve the booking by ID
    Then the response status code should be 200
    And the response should contain correct booking details

  @UpdateBooking
  Scenario: Update an existing booking
    Given a booking exists with ID
    When I update the booking with new details:
      | firstname | UpdatedName       |
      | lastname  | UpdatedLast       |
      | email     | updated@gmail.com |
    Then the response status code should be 200
    And the response should contain updated details

  @CancelBooking
  Scenario: Delete a booking
    Given a booking exists with ID
    When I delete the booking
    Then the response status code should be 201
    And the booking should be deleted

  @InvalidData
  Scenario Outline: To post booking request with invalid booking details
    Given user enters invalid booking details "<firstName>", "<lastName>", "<depositPaid>","<email>","<phone>","<checkIn>","<checkOut>","<roomId>"
    When user sends POST request to create a booking
    Then response code should be 400
    And booking response should contain the error message "<errorMessage>"

    Examples:
      | firstName           | lastName            | depositPaid | email                     | phone                  | checkIn    | checkOut   | roomId | errorMessage                        |
      |                     | Automation          | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 230    | Firstname should not be blank       |
      | KataApiTestsBDDJava | Automation          | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 231    | size must be between 3 and 18       |
      | ka                  | Automation          | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 232    | size must be between 3 and 18       |
      | KataApi             |                     | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 233    | Lastname should not be blank        |
      | KataApi             | KataApiTestsBDDJava | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 234    | size must be between 3 and 30       |
      | KataApi             | ka                  | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 | 2025-11-15 | 235    | size must be between 3 and 30       |
      | KataApi             | Automation          | true        |                           | 985345334903           | 2025-10-31 | 2025-11-15 | 236    | must not be empty                   |
      | KataApi             | Automation          | true        | tAutomation0@testmail.com |                        | 2025-10-31 | 2025-11-15 | 237    | must not be empty                   |
      | KataApi             | Automation          | true        | tAutomation0@testmail.com | +123456789101187654321 | 2025-10-31 | 2025-11-15 | 238    | size must be between 11 and 21      |
      | KataApi             | Automation          | true        | tAutomation0@testmail.com | +123456789             | 2025-10-31 | 2025-11-15 | 239    | size must be between 11 and 21      |
      | KataApi             | Automation          | true        | tAutomation0@testmail.com | 985345334903           |            | 2025-11-15 | 240    | must not be null                    |
      | KataApi             | Automation          | true        | tAutomation0@testmail.com | 985345334903           | 2025-10-31 |            | 241    | must not be null                    |
      | KataApi             | Automation          | true        | @gmail.com                | 985345334903           | 2025-10-31 | 2025-11-15 | 242    | must be a well-formed email address |