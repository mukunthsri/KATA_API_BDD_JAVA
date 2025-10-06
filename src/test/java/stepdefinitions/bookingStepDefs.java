package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pages.BookingAPI;
import utils.ConfigManager;

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
        System.out.println(ConfigManager.getBaseURI() + "api/booking/");
        // if (response.getStatusCode() == 201) {
        //   bookingId = response.jsonPath().getInt("bookingid");
        //}
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