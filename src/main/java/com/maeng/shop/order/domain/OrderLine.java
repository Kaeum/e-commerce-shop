package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.product.domain.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
public class OrderLine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String size;

    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    protected OrderLine() {}

    public OrderLine(Product product, String size, int orderPrice, Order order) {
        this.product = product;
        this.size = size;
        this.orderPrice = orderPrice;
        this.order = order;
    }

    public static OrderLine createOrderLine(Product product, String size, int orderPrice, Order order) {
        return new OrderLine(product, size, orderPrice, order);
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
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
