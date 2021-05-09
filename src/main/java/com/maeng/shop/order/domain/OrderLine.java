package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.product.domain.Item;

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

    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    protected OrderLine() {}

    public OrderLine(Item item, String size, int orderPrice, Order order) {
        this.item = item;
        this.size = size;
        this.orderPrice = orderPrice;
        this.order = order;
    }

    public static OrderLine createOrderLine(Item item, String size, int orderPrice, Order order) {
        return new OrderLine(item, size, orderPrice, order);
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
