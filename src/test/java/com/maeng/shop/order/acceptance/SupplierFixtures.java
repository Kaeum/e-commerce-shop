package com.maeng.shop.order.acceptance;

import com.maeng.shop.order.dto.ItemDto;
import com.maeng.shop.order.dto.SupplierDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierFixtures {
    private SupplierFixtures() {}

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
