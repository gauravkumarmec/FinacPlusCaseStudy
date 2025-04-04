package com.finacplus.qa.testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetApiTest {

    @Test
    public void verifyGetRequestStatusCode() {
        // Base URI of the API

        RestAssured.baseURI = "https://reqres.in";

        // Make a GET request to the API
        Response response = RestAssured.get("/api/users?page=2");

        // Validate that the response code is 200
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200, "Expected status code is 200, but found " + statusCode);
    }
}