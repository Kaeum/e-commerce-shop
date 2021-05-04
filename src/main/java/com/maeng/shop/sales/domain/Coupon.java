package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
public class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
