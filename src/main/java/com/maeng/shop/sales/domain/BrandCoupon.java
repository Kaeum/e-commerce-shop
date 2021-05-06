package com.maeng.shop.sales.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "brand")
public class BrandCoupon extends Coupon{

    @OneToOne(fetch = FetchType.LAZY)
    private Supplier supplier;
}
