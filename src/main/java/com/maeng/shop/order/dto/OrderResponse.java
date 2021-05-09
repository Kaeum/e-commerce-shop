package com.maeng.shop.order.dto;

import com.maeng.shop.customer.dto.CustomerResponse;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderState;

public class OrderResponse {

    private Long id;

    private CustomerResponse customer;

    private OrderState orderState;

    public OrderResponse(Long id, CustomerResponse customer, OrderState orderState) {
        this.id = id;
        this.customer = customer;
        this.orderState = orderState;
    }

    public Long getId() {
        return id;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                CustomerResponse.toResponse(order.getCustomer()),
                order.getOrderState()
        );
    }
}
