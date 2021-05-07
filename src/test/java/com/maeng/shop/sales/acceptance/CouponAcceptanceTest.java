package com.maeng.shop.sales.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 쿠폰 관련된 기능을 테스트한다.")
public class CouponAcceptanceTest extends AcceptanceTest {
    private Long supplierId;

    @BeforeEach
    public void setUp() {
        super.setUp();
        Map<String, String> supplier = SupplierFixtures.요청_거래처_맵_생성("Nike");
        supplierId = Long.parseLong(String.valueOf(SupplierFixtures.새로운_거래처_등록(supplier)
                .as(CommonResponse.class)
                .getReturnData()));
    }

    @Test
    @DisplayName("Scenario: 쿠폰을 생성한다.")
    void createCoupon() {
        // given
        Map<String, String> coupon = new HashMap<>();
        coupon.put("type", "brand");
        coupon.put("name", "나이키 5% 할인 쿠폰");
        coupon.put("discountRate", "5");
        coupon.put("maxAmount", "10000");
        coupon.put("supplierId", "1");

        // when
        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .body(coupon)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/coupons")
                .then()
                    .log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
