package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.sales.exception.CannotCancelException;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    protected Order() {}

    public static Order createOrder(Customer customer) {
        return new Order(customer, OrderState.NEW);
    }

    private Order(Customer customer, OrderState orderState) {
        this.customer = customer;
        this.orderState = orderState;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void cancelOrder() {
        checkOrderCanBeCanceled();

        this.orderState = OrderState.CANCEL;
    }

    private void checkOrderCanBeCanceled() {
        if(!OrderState.NEW.equals(orderState)) {
            throw new CannotCancelException();
        }
    }
}
