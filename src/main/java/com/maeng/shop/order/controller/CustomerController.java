package com.maeng.shop.order.controller;

import com.maeng.shop.order.application.CustomerService;
import com.maeng.shop.order.application.OrderService;
import com.maeng.shop.order.dto.OrderDto;
import com.maeng.shop.order.dto.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public CustomerController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping(path = "/api/v1/customers")
    public ResponseEntity<Void> signUp(@RequestBody final SignupRequest signupRequest) {
        customerService.signUp(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping(path = "customers/{customerId}/orders")
    public List<OrderDto> getOrders(@PathVariable Long customerId) {
        return orderService.getOrders();
    }
}
