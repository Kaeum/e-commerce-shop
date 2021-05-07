package com.maeng.shop.sales.controller;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.sales.application.CouponService;
import com.maeng.shop.sales.dto.CreateCouponRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping(path = "/api/v1/coupons")
    public ResponseEntity<CommonResponse> createCoupon(
        @RequestBody final CreateCouponRequest couponRequest
    ) {
        Long couponId = couponService.createCoupon(couponRequest);

        return ResponseEntity
                .created(URI.create("/api/v1/coupons/" + couponId))
                .body(CommonResponse.onSuccess(couponId));
    }
}
