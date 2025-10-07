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
      | firstName | lastName   | depositPaid | email                     | phone        | checkIn    | checkOut   | roomId | errorMessage                  |
      | KATA      | Automation | true        | tAutomation0@testmail.com | 985345334903 | 2025-10-31 | 2025-11-15 |        | Firstname should not be blank |