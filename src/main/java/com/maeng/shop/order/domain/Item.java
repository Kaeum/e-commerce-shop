package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int unitPrice;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;
}
