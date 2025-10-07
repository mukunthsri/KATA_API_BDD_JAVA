@CreateBookingInvalidDetails
Feature: User enters Invalid Booking details
  As an user
  I want to perform booking operations
  So that I can manage hotel bookings

  @InvalidData
  Scenario Outline: User should get expected error message when trying to create a booking using invalid data
    Given user enters booking details
      | firstName   | lastName   | depositPaid   | email   | phone   | checkIn   | checkOut   | roomId   |
      | <firstName> | <lastName> | <depositPaid> | <email> | <phone> | <checkIn> | <checkOut> | <roomId> |
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