package com.maeng.shop.order.dto;

import com.maeng.shop.customer.Customer;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderLine;
import com.maeng.shop.order.domain.OrderState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private OrderDto() {}

    public OrderDto(Long id, Customer customer, OrderState orderState, List<OrderLineDto> orderLines) {
        this.id = id;
        this.customer = customer;
        this.orderState = orderState;
        this.orderLines = orderLines;
    }

    private Long id;

    private Customer customer;

    private OrderState orderState;

    private List<OrderLineDto> orderLines = new ArrayList<>();

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomer(),
                order.getOrderState(),
                order.getOrderLines()
                        .stream()
                        .map(OrderLineDto::toDto)
                        .collect(Collectors.toList())
        );
    }
}
