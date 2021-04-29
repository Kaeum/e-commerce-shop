package com.maeng.shop.sales.acceptance;

import com.maeng.shop.sales.dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFixtures {
    private OrderFixtures() {}

    public static Map<String, Object> 요청_주문_맵_생성(Map... maps) {
        Map<String, Object> order = new HashMap<>();

        List<Map<String, String>> orderLines = new ArrayList<>();

        for(Map<String, String> m : maps) {
            orderLines.add(m);
        }

        order.put("orderLines", orderLines);
        return order;
    }

    public static Map<String, String> 요청_주문품목_맵_생성(Long itemId, String size) {
        Map<String, String> itemOne = new HashMap<>();
        itemOne.put("itemId", itemId + "");
        itemOne.put("size", size);
        return itemOne;
    }

    public static ExtractableResponse<Response> 주문하기(Long customerId, Map<String, Object> order) {
        return RestAssured
                .given()
                .log().all()
                .body(order)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/customers/" + customerId + "/orders")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 주문취소(Long customerId, Long orderId) {
        return RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/v1/customers/"+customerId+"/orders/" + orderId)
                .then().log().all()
                .extract();
    }

    public static OrderDto 주문_조회(Long customerId, Long orderId) {
        return RestAssured
                .given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/customers/"+customerId+"/orders/"+orderId)
                .then()
                .log().all()
                .extract()
                .as(OrderDto.class);
    }

    public static ExtractableResponse<Response> 주문_목록_조회(Long customerId) {
        return RestAssured
                .given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/customers/"+ customerId +"/orders")
                .then()
                .log().all()
                .extract();
    }
}
