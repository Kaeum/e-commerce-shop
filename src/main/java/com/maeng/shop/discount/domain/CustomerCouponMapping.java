package com.maeng.shop.discount.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.customer.domain.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_coupon_mappings")
public class CustomerCouponMapping extends BaseEntity {

    private static final String VALID_YN_Y = "Y";
    private static final int DEFAULT_EXPIRE_DURATION = 1;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private String validYn;

    private LocalDateTime expiresAt;

    protected CustomerCouponMapping() {}

    public CustomerCouponMapping(Customer customer, Coupon coupon) {
        this.customer = customer;
        this.coupon = coupon;
        this.validYn = VALID_YN_Y;
        this.expiresAt = LocalDateTime.now().plusMonths(DEFAULT_EXPIRE_DURATION);
    }
}
