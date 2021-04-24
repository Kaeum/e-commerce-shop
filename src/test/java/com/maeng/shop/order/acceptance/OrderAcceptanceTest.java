package com.maeng.shop.order.acceptance;

import com.maeng.shop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderAcceptanceTest extends AcceptanceTest {
    private Long customerId;

    @BeforeEach
    public void setUp() {
        super.setUp();
        String email = "newmember1@email.com";
        String password = "no_one_knows123";
        String age = "20";
        String name = "김철수";
        customerId = CustomerFixtures.회원가입(email, password, age, name).as(Long.class);
    }

    @Test
    void getOrders() {
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .get("/api/v1/customers/"+customerId+"/orders")
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
        Map<String, Object> order = new HashMap<>();

        List<Map<String, String>> orderLines = new ArrayList<>();
        Map<String, String> itemOne = new HashMap<>();
        Map<String, String> itemTwo = new HashMap<>();
        itemOne.put("itemId", "1");
        itemOne.put("size", "L");
        itemTwo.put("itemId", "2");
        itemTwo.put("size", "S");
        orderLines.add(itemOne);
        orderLines.add(itemTwo);

        order.put("orderLines", orderLines);

        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .body(order)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/customers/"+customerId+"/orders")
                .then()
                    .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
