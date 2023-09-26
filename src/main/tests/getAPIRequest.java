package com.testautomation.apitesting.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utilities;

public class getAPIRequest extends Utilities {

    @Test()
    public void getAllBookings() {

        Response response =
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .get()

                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Server", "Cowboy")
                .contentType("application/json; charset=utf-8")

                .extract()
                .response();
        //System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains("bookingid"));
    }
}
