package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
public class OrderLine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Category getCategory() {
        return category;
    }

    public Order getOrder() {
        return order;
    }
}
