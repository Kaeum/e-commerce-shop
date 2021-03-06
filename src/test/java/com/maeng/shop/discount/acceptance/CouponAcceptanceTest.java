package com.maeng.shop.discount.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.customer.acceptance.CustomerFixtures;
import com.maeng.shop.supplier.acceptance.SupplierFixtures;
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
    private Long customerId;

    @BeforeEach
    public void setUp() {
        super.setUp();
        Map<String, String> supplier = SupplierFixtures.요청_거래처_맵_생성("Nike");
        supplierId = Long.parseLong(
                    String.valueOf(SupplierFixtures.새로운_거래처_등록(supplier)
                        .as(CommonResponse.class)
                        .getReturnData()
                    )
        );

        Map<String, String> customer = CustomerFixtures.요청_고객_맵_생성("newmember1@email.com", "noone!knows123","20","김철수");
        customerId = Long.parseLong(
                    String.valueOf(CustomerFixtures.회원가입(customer)
                        .as(CommonResponse.class)
                        .getReturnData()
                    )
        );
    }

    @Test
    @DisplayName("Scenario: 쿠폰을 생성한다.")
    void createCoupon() {
        // given
        Map<String, String> coupon = CouponFixtures.요청_브랜드_쿠폰_맵_생성("brand", "나이키 5% 할인 쿠폰", "5", "10000", supplierId);

        // when
        ExtractableResponse<Response> response = CouponFixtures.쿠폰_생성(coupon);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Scenario: 유저에게 회원등급 쿠폰을 지급한다.")
    void issueCoupon_MemberLevelCoupon() {
        // given
        Map<String, String> coupon = CouponFixtures.요청_멤버_쿠폰_맵_생성("member", "브론즈 회원 5% 할인 쿠폰", "5", "10000", "BRONZE");
        Long couponId = Long.parseLong(String.valueOf(CouponFixtures.쿠폰_생성(coupon).as(CommonResponse.class).getReturnData()));

        // when
        ExtractableResponse<Response> response = CouponFixtures.쿠폰_발급(coupon, customerId, couponId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Scenario: 유저에게 브랜드 쿠폰을 지급한다.")
    void issueCoupon_BrandCoupon() {
        // given
        Map<String, String> coupon = CouponFixtures.요청_브랜드_쿠폰_맵_생성("brand", "나이키 5% 할인 쿠폰", "5", "10000", supplierId);
        Long couponId = Long.parseLong(String.valueOf(CouponFixtures.쿠폰_생성(coupon).as(CommonResponse.class).getReturnData()));

        // when
        ExtractableResponse<Response> response = CouponFixtures.쿠폰_발급(coupon, customerId, couponId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Scenario: 유저의 등급에 맞지 않는 회원등급 쿠폰을 지급하면 실패한다.")
    void issueCoupon_withInappropriateCoupon() {
        // given
        Map<String, String> coupon = CouponFixtures.요청_멤버_쿠폰_맵_생성("member", "나이키 5% 할인 쿠폰", "7", "15000", "SILVER");
        Long couponId = Long.parseLong(String.valueOf(CouponFixtures.쿠폰_생성(coupon).as(CommonResponse.class).getReturnData()));

        // when
        ExtractableResponse<Response> response = CouponFixtures.쿠폰_발급(coupon, customerId, couponId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}