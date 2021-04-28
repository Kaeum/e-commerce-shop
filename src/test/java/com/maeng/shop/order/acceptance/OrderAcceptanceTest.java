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

        Map<String, String> supplier = new HashMap<>();
        supplier.put("companyName", "Nike");

        Long supplierId = SupplierFixtures.새로운_거래처_등록(supplier).as(Long.class);

        Map<String, String> itemOne = new HashMap<>();
        itemOne.put("name", "Nike Air Force 1 '07");
        itemOne.put("unitPrice", "130000");
        itemOne.put("sex", "MAN");
        itemOne.put("category", "SHOES");

        Map<String, String> itemTwo = new HashMap<>();
        itemTwo.put("name", "Nike NRG Hoody");
        itemTwo.put("unitPrice", "100000");
        itemTwo.put("sex", "MAN");
        itemTwo.put("category", "CLOTHES");

        SupplierFixtures.새로운_아이템_등록(supplierId, itemOne);
        SupplierFixtures.새로운_아이템_등록(supplierId, itemTwo);
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
