package com.maeng.shop.discount.controller;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.discount.application.CouponService;
import com.maeng.shop.discount.dto.CreateCouponRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping(path = "/api/v1/customers/{customerId}/coupons/{couponId}")
    public ResponseEntity<CommonResponse> issueCoupon(
            @PathVariable Long customerId,
            @PathVariable Long couponId
    ) {
        couponService.issueCoupon(customerId, couponId);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
