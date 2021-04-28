package com.maeng.shop.sales.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierFixtures {
    private SupplierFixtures() {}

    public static Map<String, String> 요청_거래처_맵_생성(String companyName) {
        Map<String, String> supplier = new HashMap<>();
        supplier.put(companyName, companyName);
        return supplier;
    }

    public static Map<String, String> 요청_아이템_맵_생성(String name, String unitPrice, String sex, String category) {
        Map<String, String> item = new HashMap<>();
        item.put("name", name);
        item.put("unitPrice", unitPrice);
        item.put("sex", sex);
        item.put("category", category);
        return item;
    }

    public static ExtractableResponse<Response> 새로운_거래처_등록(Map<String, String> supplier) {
        return RestAssured
                .given()
                    .log().all()
                    .body(supplier)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/suppliers")
                .then()
                    .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 새로운_아이템_등록(Long supplierId, Map<String, String> item) {
        return RestAssured
                .given()
                    .log().all()
                    .body(item)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/suppliers/"+supplierId+"/items")
                .then()
                    .log().all()
                .extract();
    }

    public static void 아이템_등록_확인(ExtractableResponse<Response> response, Map<String, String> item, Long supplierId) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        ExtractableResponse<Response> getResponse = RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/api/v1/suppliers/" + supplierId + "/items")
                .then()
                    .log().all()
                .extract();

        assertThat(getResponse.body().jsonPath().getString("[0].name")).isEqualTo("Nike Air Force 1 '07");

    }
}
