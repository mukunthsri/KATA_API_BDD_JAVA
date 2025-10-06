package com.booking.stepdefinitions;

import com.booking.pages.BookingAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class bookingStepDefs {

    private BookingAPI bookingAPI = new BookingAPI();
    private Response response;
    private static int bookingId;

    @Given("the base URI is set to {string}")
    public void setBaseURI(String baseURI) {
        bookingAPI.setBaseURI(baseURI);
    }

    @When("I create a booking with following details:")
    public void createBooking(io.cucumber.datatable.DataTable dataTable) {
        var bookingData = dataTable.asMap(String.class, String.class);
        response = bookingAPI.createBooking(
                bookingData.get("firstname"),
                bookingData.get("lastname"),
                bookingData.get("email"),
                bookingData.get("phone"),
                bookingData.get("checkin"),
                bookingData.get("checkout"),
                Boolean.parseBoolean(bookingData.get("deposit")),
                Integer.parseInt(bookingData.get("roomid"))
        );
        if (response.getStatusCode() == 201) {
            bookingId = response.jsonPath().getInt("bookingid");
        }
    }

    @Given("a booking exists with ID")
    public void bookingExists() {
        if (bookingId == 0) {
            // Create a booking first if none exists
            response = bookingAPI.createBooking(
                    "KataApi", "Automation", "apikata@gmail.com",
                    "+23423252623", "2025-10-20", "2025-10-21", false, 2
            );
            bookingId = response.jsonPath().getInt("bookingid");
        }
    }

    @When("I retrieve the booking by ID")
    public void retrieveBooking() {
        response = bookingAPI.getBooking(bookingId);
    }

    @Given("user enters invalid booking details {string}, {string}, {string},{string},{string},{string},{string},{string}")
    public void user_enters_invalid_booking_details(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {

    }

    @When("user sends POST request to create a booking")
    public void user_sends_post_request_to_create_a_booking() {

    }

    @Then("response code should be {int}")
    public void response_code_should_be(Integer int1) {

    }

    @Then("booking response should contain the error message {string}")
    public void booking_response_should_contain_the_error_message(String string) {

    }

    @When("I update the booking with new details:")
    public void updateBooking(io.cucumber.datatable.DataTable dataTable) {
        var updateData = dataTable.asMap(String.class, String.class);

        response = bookingAPI.updateBooking(
                bookingId,
                updateData.get("firstname"),
                updateData.get("lastname"),
                updateData.get("email")
        );
    }

    @When("I delete the booking")
    public void deleteBooking() {
        response = bookingAPI.deleteBooking(bookingId);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain booking details")
    public void verifyBookingDetails() {
        assertNotNull(response.jsonPath().getString("booking.firstname"));
        assertNotNull(response.jsonPath().getString("booking.lastname"));
        assertNotNull(response.jsonPath().getString("booking.bookingdates.checkin"));
        assertNotNull(response.jsonPath().getString("booking.bookingdates.checkout"));
    }

    @Then("the booking ID should be generated")
    public void verifyBookingId() {
        assertTrue(bookingId > 0);
    }

    @Then("the response should contain correct booking details")
    public void verifyCorrectBookingDetails() {
        assertEquals("KataApi", response.jsonPath().getString("firstname"));
        assertEquals("Automation", response.jsonPath().getString("lastname"));
    }

    @Then("the response should contain updated details")
    public void verifyUpdatedDetails() {
        assertEquals("UpdatedName", response.jsonPath().getString("firstname"));
        assertEquals("UpdatedLast", response.jsonPath().getString("lastname"));
    }

    @Then("the booking should be deleted")
    public void verifyBookingDeleted() {
        Response getResponse = bookingAPI.getBooking(bookingId);
        assertEquals(404, getResponse.getStatusCode());
    }
}