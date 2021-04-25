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

    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public OrderLine(Item item, String size) {
        this.item = item;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public String getSize() {
        return size;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
