package com.maeng.shop.sales.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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
        Map<String, String> coupon = CouponFixtures.요청_쿠폰_맵_생성("brand", "나이키 5% 할인 쿠폰", "5", "10000", supplierId);

        // when
        ExtractableResponse<Response> response = CouponFixtures.쿠폰_생성(coupon);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}