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

import static com.maeng.shop.order.acceptance.SupplierFixtures.요청_아이템_맵_생성;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderAcceptanceTest extends AcceptanceTest {
    private Long customerId;
    private Long itemOneId;
    private Long itemTwoId;
    @BeforeEach
    public void setUp() {
        super.setUp();

        Map<String, String> customer = CustomerFixtures.요청_고객_맵_생성("newmember1@email.com", "noone!knows123","20","김철수");
        customerId = CustomerFixtures.회원가입(customer).as(Long.class);

        Map<String, String> supplier = SupplierFixtures.요청_거래처_맵_생성("Nike");
        Long supplierId = SupplierFixtures.새로운_거래처_등록(supplier).as(Long.class);

        Map<String, String> itemOne = 요청_아이템_맵_생성("Nike Air Force 1 '07", "130000", "MAN", "SHOES");
        Map<String, String> itemTwo = 요청_아이템_맵_생성("Nike NRG Hoody", "100000", "MAN", "CLOTHES");

        itemOneId = SupplierFixtures.새로운_아이템_등록(supplierId, itemOne).as(Long.class);
        itemTwoId = SupplierFixtures.새로운_아이템_등록(supplierId, itemTwo).as(Long.class);
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
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "L");
        Map<String, String> orderLineTwo = OrderFixtures.요청_주문품목_맵_생성(itemTwoId, "S");
        Map<String, Object> order = OrderFixtures.요청_주문_맵_생성(orderLineOne, orderLineTwo);

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
