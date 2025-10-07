package com.booking.models;

import com.google.gson.annotations.SerializedName;
import io.cucumber.java.it.Data;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @SerializedName("bookingid")
    private int bookingId;

    @SerializedName("roomid")
    private int roomId;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("depositpaid")
    private boolean depositPaid;

    private String email;

    private String phone;

    @SerializedName("bookingdates")
    private BookingDates bookingDates;
}
