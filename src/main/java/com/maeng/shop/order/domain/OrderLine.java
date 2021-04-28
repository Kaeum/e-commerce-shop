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

    protected OrderLine() {}

    private OrderLine(Item item, String size, Order order) {
        this.item = item;
        this.size = size;
        this.order = order;
    }

    public static OrderLine createOrderLine(Item item, String size, Order order) {
        return new OrderLine(item, size, order);
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
