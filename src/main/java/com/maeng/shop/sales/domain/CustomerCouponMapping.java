package com.maeng.shop.sales.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_coupon_mappings")
public class CustomerCouponMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private boolean validYn;

    private LocalDateTime expiredAt;
}
