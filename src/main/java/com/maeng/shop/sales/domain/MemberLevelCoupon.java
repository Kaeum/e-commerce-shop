package com.maeng.shop.sales.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "member")
public class MemberLevelCoupon extends Coupon{

    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel;
}
