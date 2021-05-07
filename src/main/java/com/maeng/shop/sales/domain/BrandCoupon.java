package com.maeng.shop.sales.domain;

import javax.persistence.*;

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
