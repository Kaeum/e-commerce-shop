package com.maeng.shop.sales.controller;

import com.maeng.shop.sales.application.CustomerService;
import com.maeng.shop.sales.application.OrderService;
import com.maeng.shop.sales.dto.OrderDto;
import com.maeng.shop.sales.dto.PlaceOrderRequest;
import com.maeng.shop.sales.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public CustomerController(final OrderService orderService, final CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostMapping(path = "/api/v1/customers")
    public ResponseEntity<Long> signUp(@RequestBody final SignupRequest signupRequest) {
        Long customerId = customerService.signUp(signupRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/customers/" + customerId))
                .body(customerId);
    }

    @PostMapping(path = "/api/v1/customers/{customerId}/orders")
    public ResponseEntity<Long> placeOrder(
            @PathVariable final Long customerId,
            @RequestBody final PlaceOrderRequest placeOrderRequest)
    {
        Long orderId = orderService.placeOrder(customerId, placeOrderRequest);
        return ResponseEntity.created(URI.create("/api/v1/customers/"+customerId+"/orders/"+orderId))
                .body(orderId);
    }

    @GetMapping(path = "/api/v1/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable final Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping(path = "/api/v1/customers/{customerId}/orders")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable final Long customerId) {
        return ResponseEntity.ok(orderService.getOrders(customerId));
    }

    @DeleteMapping(path = "/api/v1/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Void> cancelOrder(
        @PathVariable final Long orderId
    ) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
