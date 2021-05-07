package com.maeng.shop.sales.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "brand")
public class BrandCoupon extends Coupon{

    @OneToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    public BrandCoupon() {}

    public BrandCoupon(
            final String name,
            final int discountRate,
            final int maxAmount,
            final Supplier supplier
    ) {
        super(name, discountRate, maxAmount);
        this.supplier = supplier;
    }
}
