package com.maeng.shop.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class OrderAcceptanceTest {

    @Test
    void getOrders() {
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .get("/api/v1/customers/1/orders")
                .then()
                    .log().all()
                .extract();
    }

    @Test
    void getOrderDetail() {
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .get("/api/v1/customers/1/orders/1")
                .then()
                    .log().all()
                .extract();
    }

    @Test
    void placeOrder() {
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/customers/1/orders")
                .then()
                    .log().all()
                .extract();
    }
}
