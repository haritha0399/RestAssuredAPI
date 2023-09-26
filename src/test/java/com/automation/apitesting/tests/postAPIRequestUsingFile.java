package com.automation.apitesting.tests;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FilePath;
import utils.Utilities;

import java.io.File;
import java.io.IOException;

public class postAPIRequestUsingFile extends Utilities  {

    @Test
    public void postAPICallUsingFile() throws IOException {

        String postAPIRequestBody = FileUtils.readFileToString(new File(FilePath.POST_API_FILE), "UTF-8");

        Response response =
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postAPIRequestBody)
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .post()

                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Haritha Navanee"))

                .extract()
                .response();

        String jsonArray = JsonPath.read(response.body().asString(), "$.booking.firstname");
//        String fname = (String) jsonArray.get(0);
        Assert.assertEquals(jsonArray, "Haritha Navanee");

//

        int bookingId = JsonPath.read(response.body().asString(), "$.bookingid");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("b_id", bookingId)
                .baseUri("https://restful-booker.herokuapp.com/booking")

                .when()
                .get("{b_id}")

                .then()
                .assertThat()
                .statusCode(200)

                .extract()
                .response();


    }
}
