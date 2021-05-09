package com.maeng.shop.coupon.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class CouponFixtures {
    public static Map<String, String> 요청_브랜드_쿠폰_맵_생성(String brand, String name, String discountRate, String maxAmount, Long supplierId) {
        Map<String, String> coupon = new HashMap<>();
        coupon.put("type", brand);
        coupon.put("name", name);
        coupon.put("discountRate", discountRate);
        coupon.put("maxAmount", maxAmount);
        coupon.put("supplierId", supplierId+"");
        return coupon;
    }

    public static Map<String, String> 요청_멤버_쿠폰_맵_생성(String brand, String name, String discountRate, String maxAmount, String memberLevel) {
        Map<String, String> coupon = new HashMap<>();
        coupon.put("type", brand);
        coupon.put("name", name);
        coupon.put("discountRate", discountRate);
        coupon.put("maxAmount", maxAmount);
        coupon.put("memberLevel", memberLevel);
        return coupon;
    }

    public static ExtractableResponse<Response> 쿠폰_생성(Map<String, String> coupon) {
        return RestAssured
                .given()
                    .log().all()
                    .body(coupon)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/coupons")
                .then()
                    .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 쿠폰_발급(Map<String, String> coupon, Long customerId, Long couponId) {
        return RestAssured.given().log().all().contentType(MediaType.APPLICATION_JSON_VALUE).body(coupon)
                .when().post("/api/v1/customers/" + customerId + "/coupons/" + couponId)
                .then().log().all().extract();
    }
}
