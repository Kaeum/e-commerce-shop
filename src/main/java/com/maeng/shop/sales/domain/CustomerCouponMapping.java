package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_coupon_mappings")
public class CustomerCouponMapping extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private String validYn;

    private LocalDateTime expiredAt;

    protected CustomerCouponMapping() {}

    public CustomerCouponMapping(Customer customer, Coupon coupon) {
        this.customer = customer;
        this.coupon = coupon;
        this.validYn = "Y";
        this.expiredAt = LocalDateTime.now().plusMonths(1);
    }
}
