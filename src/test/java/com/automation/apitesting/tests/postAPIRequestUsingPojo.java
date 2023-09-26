package com.automation.apitesting.tests;

import com.automation.apitesting.tests.pojos.BookingDates;
import com.automation.apitesting.tests.pojos.BookingDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utils.FilePath;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class postAPIRequestUsingPojo {

    @Test
    public void postAPIRequest() throws IOException {
        try {
        String schema = FileUtils.readFileToString(new File(FilePath.JSON_SCHEMA), "UTF-8");

        String requestBody;

            BookingDates dates = new BookingDates("2023-01-03", "2023-02-04");

            BookingDetails details = new BookingDetails("Hari", "snk", "books", 304, true, dates);

            //Used to convert java objects into json objects
//          //serialization
            ObjectMapper objectmapper = new ObjectMapper();
            requestBody = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(details);

            System.out.println(requestBody);

            //deserialization
            BookingDetails booking = objectmapper.readValue(requestBody,BookingDetails.class );
            System.out.println(booking.getFirstname());

            System.out.println(booking.getBookingdates().getCheckin());


            System.out.println("Print schemaa----->^>" + schema);
            Response response =
            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .baseUri("https://restful-booker.herokuapp.com/booking")

                    .when()
                    .post()

                    .then()
                    .statusCode(200)

                    .extract()
                    .response();

            int bookingId = response.path("bookingid");

            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
//                    .pathParam("b_id", bookingId)
                    .baseUri("https://restful-booker.herokuapp.com/booking")

                    .when()
                    .get("/{b_id}", bookingId)

                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("firstname", Matchers.equalTo("Hari"));
//                    .body(JsonSchemaValidator.matchesJsonSchema(schema));

        } catch (Exception e){
            System.out.println(e);
        }




    }
}
