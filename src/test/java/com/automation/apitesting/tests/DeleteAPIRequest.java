package com.automation.apitesting.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Utilities;

import java.io.File;
import java.io.IOException;

import static utils.FilePath.*;
import static utils.FilePath.PATCH_API_REQUEST_BODY;

public class DeleteAPIRequest extends Utilities {

    @Test
    public void EndToEndApi() throws IOException {

        String postCallBodyRequest =  FileUtils.readFileToString(new File(POST_API_FILE),"UTF-8");
        String tokenBodyRequest =  FileUtils.readFileToString(new File(TOKEN_API_REQUEST_BODY),"UTF-8");
        String putBodyRequest =  FileUtils.readFileToString(new File(PUT_API_REQUEST_BODY),"UTF-8");
        String patchBodyRequest = FileUtils.readFileToString(new File(PATCH_API_REQUEST_BODY), "UTF-8");

        //Create a post call
        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(postCallBodyRequest)
                        .baseUri("https://restful-booker.herokuapp.com/booking")

                        .when()
                        .post()

                        .then()
                        .assertThat()
                        .statusCode(200)

                        .extract()
                        .response();

        int bookingId = response.path("bookingid");

        //verify the newly created data
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .get("/{b_id}", bookingId)

                .then()
                .assertThat()
                .statusCode(200);


        //to generate a token
        Response response2 =
                RestAssured
                        .given()
                        .baseUri("https://restful-booker.herokuapp.com/auth")
                        .contentType(ContentType.JSON)
                        .body(tokenBodyRequest)

                        .when()
                        .post()

                        .then()
                        .statusCode(200)

                        .extract()
                        .response();

        String tokenValue = response2.path("token");

        //to pass the token value in put call api

        RestAssured
                .given()
                .body(putBodyRequest)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+tokenValue)
                .baseUri("https://restful-booker.herokuapp.com/booking")
//                .pathParam("b_id", bookingId)

                .when()
                .put("/{b_id}", bookingId)

                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Vihani"))

                .extract()
                .response();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+tokenValue)
                .body(patchBodyRequest)
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .patch("/{b_id}",bookingId)

                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Vinayagar"))

                .extract()
                .response();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token="+tokenValue)
                .baseUri("https://restful-booker.herokuapp.com/booking")
//                .pathParam("b_id", bookingId)

                .when()
                .delete("/{b_id}",bookingId)

                .then()
                .assertThat()
                .statusCode(201);
    }
}
