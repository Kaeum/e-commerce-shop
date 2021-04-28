package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Order;
import com.maeng.shop.sales.domain.OrderState;

public class OrderDto {

    private Long id;

    private CustomerDto customer;

    private OrderState orderState;

    public OrderDto(Long id, CustomerDto customer, OrderState orderState) {
        this.id = id;
        this.customer = customer;
        this.orderState = orderState;
    }

    public Long getId() {
        return id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                CustomerDto.toDto(order.getCustomer()),
                order.getOrderState()
        );
    }
}
