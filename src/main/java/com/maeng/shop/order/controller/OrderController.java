package com.maeng.shop.order.controller;

import com.maeng.shop.order.application.OrderService;
import com.maeng.shop.order.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "customers/{customerId}/orders")
    public List<OrderDto> getOrders() {
        return orderService.getOreders();
    }
}
