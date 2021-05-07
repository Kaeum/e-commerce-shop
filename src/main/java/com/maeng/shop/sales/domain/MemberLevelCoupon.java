package com.maeng.shop.sales.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "member")
public class MemberLevelCoupon extends Coupon{

    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel;

    protected MemberLevelCoupon() {}

    public MemberLevelCoupon(
            final String name,
            int discountRate,
            int maxAmount,
            MemberLevel memberLevel
    ) {
        super(name, discountRate, maxAmount);
        this.memberLevel = memberLevel;
    }
}
