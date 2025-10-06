@Positive @ValidCredentials
Feature: Booking API Operations
  As an user
  I want to perform booking operations
  So that I can manage hotel bookings

  Background:
    Given the base URI is set to "https://automationintesting.online/"

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

  Scenario: Get booking by ID
    Given a booking exists with ID
    When I retrieve the booking by ID
    Then the response status code should be 200
    And the response should contain correct booking details

  Scenario: Update an existing booking
    Given a booking exists with ID
    When I update the booking with new details:
      | firstname | UpdatedName       |
      | lastname  | UpdatedLast       |
      | email     | updated@gmail.com |
    Then the response status code should be 200
    And the response should contain updated details

  Scenario: Delete a booking
    Given a booking exists with ID
    When I delete the booking
    Then the response status code should be 201
    And the booking should be deleted