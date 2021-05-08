package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.sales.exception.InappropriateMemberLevelException;

import javax.persistence.*;


@Entity
@Table(name = "coupons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int discountRate;

    private int maxAmount;

    protected Coupon() {
    }

    protected Coupon(String name, int discountRate, int maxAmount) {
        this.name = name;
        this.discountRate = discountRate;
        this.maxAmount = maxAmount;
    }

    public Long getId() {
        return id;
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

    public void checkMemberLevel(MemberLevel memberLevel) {
        if(this instanceof BrandCoupon) {
            return;
        }

        if(!memberLevel.equals(((MemberLevelCoupon) this).getMemberLevel())) {
            throw new InappropriateMemberLevelException();
        }
    }
}
