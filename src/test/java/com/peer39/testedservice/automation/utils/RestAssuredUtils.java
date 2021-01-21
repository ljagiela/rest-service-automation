package com.peer39.testedservice.automation.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class RestAssuredUtils {
    public static void setupRestAssured() {
        RestAssured.filters(new AllureRestAssured());
        //        uncomment to debug request/responses
        //        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
