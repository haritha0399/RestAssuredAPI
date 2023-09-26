package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class Utilities {

    @BeforeMethod
    public void BeforeMethod() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


}
