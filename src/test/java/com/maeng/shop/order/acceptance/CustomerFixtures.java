package com.maeng.shop.order.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class CustomerFixtures {
    public static ExtractableResponse<Response> 회원가입(String email, String password, String age, String name) {

        Map<String, String> customer = new HashMap<>();
        customer.put("email", email);
        customer.put("password", password);
        customer.put("age", age);
        customer.put("name", name);

        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .body(customer)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/customers")
                .then()
                    .log().all()
                .extract();
        return response;
    }
}
