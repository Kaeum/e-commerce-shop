package com.maeng.shop.discount.exception;

public class NoCouponException extends RuntimeException {
    public static final String CANNOT_FIND_COUPON = "no coupon found";

    public NoCouponException() {
        super(CANNOT_FIND_COUPON);
    }

    public NoCouponException(Throwable cause) {
        super(CANNOT_FIND_COUPON, cause);
    }
}
