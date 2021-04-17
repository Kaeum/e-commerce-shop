package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private int unitPrice;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;
}
