package com.maeng.shop.order;

import com.maeng.shop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Scenario: 새로운 거래처를 등록한다.")
    void registerSupplier() {
        Map<String, String> body = new HashMap<>();
        body.put("companyName", "Nike");

        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .body(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/suppliers")
                .then()
                    .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }
}
