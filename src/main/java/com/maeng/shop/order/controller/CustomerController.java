package com.maeng.shop.order.controller;

import com.maeng.shop.order.application.OrderService;
import com.maeng.shop.order.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/")
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "customers/{customerId}/orders")
    public List<OrderDto> getOrders(@PathVariable Long customerId) {
        return orderService.getOrders();
    }
}
