package com.maeng.shop.discount.dto;

import com.maeng.shop.customer.domain.MemberLevel;
import com.maeng.shop.discount.domain.BrandCoupon;
import com.maeng.shop.discount.domain.MemberLevelCoupon;
import com.maeng.shop.supplier.domain.Supplier;

public class CreateCouponRequest {
    private static final String COUPON_TYPE_BRAND = "brand";
    private String type;
    private String name;
    private int discountRate;
    private int maxAmount;
    private MemberLevel memberLevel;
    private Long supplierId;

    public static String getCouponTypeBrand() {
        return COUPON_TYPE_BRAND;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public BrandCoupon toBrandCoupon(Supplier supplier) {
        return new BrandCoupon(name, discountRate, maxAmount, supplier);
    }

    public MemberLevelCoupon toMemberLevelCoupon() {
        return new MemberLevelCoupon(name, discountRate, maxAmount, memberLevel);
    }

    public boolean isBrandCoupon() {
        return COUPON_TYPE_BRAND.equals(type);
    }
}
