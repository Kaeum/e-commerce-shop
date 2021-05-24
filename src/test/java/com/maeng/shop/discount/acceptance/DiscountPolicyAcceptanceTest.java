package com.maeng.shop.discount.acceptance;

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

@DisplayName("Feature: 할인 정책과 관련된 기능을 처리한다.")
public class DiscountPolicyAcceptanceTest extends AcceptanceTest {

    public void setUp() {
        super.setUp();
    }

    @DisplayName("Scenario: 할인정책을 설정한다.")
    @Test
    void setDiscountPolicyTest() {
        // given
        Map<String, String> discountPolicy = new HashMap<>();

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(discountPolicy)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/products/{productId}/policy")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
