package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "coupons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private int discountRate;

    private int maxAmount;

}
