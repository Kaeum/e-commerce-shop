package com.maeng.shop.order;

import com.maeng.shop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class SupplierAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Scenario: 새로운 거래처를 등록한다.")
    void registerSupplier() {
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post()
                .then()
                    .log().all()
                .extract();
    }
}
