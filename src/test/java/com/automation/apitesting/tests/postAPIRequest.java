package com.automation.apitesting.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Utilities;

public class postAPIRequest extends Utilities {

    @Test
    public void POSTAPIRequest() {

//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //to prepare the JSON body
        JSONObject bookingDetails = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDetails.put("firstname", "Haritha");
        bookingDetails.put("lastname", "Navaneetha Krishnan");
        bookingDetails.put("totalprice", 111);
        bookingDetails.put("depositpaid", true);
        bookingDetails.put("bookingdates", bookingDates);
        bookingDetails.put("additionalneeds", "extra pillows please");

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(bookingDetails.toString())
                        .baseUri("https://restful-booker.herokuapp.com/booking")
//                .log().all()

                        .when()
                        .post()

                        .then()
                        .assertThat()
//                .log().ifValidationFails()
                        .statusCode(200)
                        .body("booking.firstname", Matchers.equalTo("Haritha"))
                        .body("booking.bookingdates.checkin", Matchers.equalTo("2018-01-01"))
                        .body("booking.additionalneeds", Matchers.equalTo("extra pillows please"))

                        .extract()
                        .response();

        int bookingId = response.path("bookingid");
        //Pass the above parameter in the next api call

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("b_id",bookingId )
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .get("{b_id}")

                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Haritha"));
    }
}

