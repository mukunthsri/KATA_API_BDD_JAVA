package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import utils.ConfigManager;

import java.util.HashMap;
import java.util.Map;

public class BookingAPI {
    private String token;
   /* private String baseURI;
    private String token;

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.baseURI = baseURI;
    }*/

    private String getAuthToken() {
        if (token == null) {
            Map<String, String> auth = new HashMap<>();
            auth.put("username", "admin");
            auth.put("password", "password");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(auth)
                    .when()
                    .post(ConfigManager.getBaseURI() + "api/auth/login");

            token = response.jsonPath().getString("token");
        }
        return token;
    }

    public Response createBooking(String firstname, String lastname, String email,
                                  String phone, String checkin, String checkout, boolean deposit) {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstname);
        booking.put("lastname", lastname);
        booking.put("email", email);
        booking.put("phone", phone);
        booking.put("bookingdates", bookingDates);
        booking.put("depositpaid", deposit);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post(ConfigManager.getBaseURI() + "api/booking/");
    }

    public Response getBooking(int bookingId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .when()
                .get("api/booking/" + bookingId);
    }

    public Response updateBooking(int bookingId, String firstname, String lastname, String email) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("firstname", firstname);
        updateData.put("lastname", lastname);
        updateData.put("email", email);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .body(updateData)
                .when()
                .put("api/booking/" + bookingId);
    }

    public Response deleteBooking(int bookingId) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + getAuthToken())
                .when()
                .delete("api/booking/" + bookingId);
    }
}