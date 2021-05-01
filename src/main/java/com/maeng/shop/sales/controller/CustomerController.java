package com.maeng.shop.sales.controller;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.sales.application.CustomerService;
import com.maeng.shop.sales.application.OrderService;
import com.maeng.shop.sales.dto.OrderDto;
import com.maeng.shop.sales.dto.PlaceOrderRequest;
import com.maeng.shop.sales.dto.SignupRequest;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CommonResponse> signUp(@RequestBody final SignupRequest signupRequest) {
        Long customerId = customerService.signUp(signupRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/customers/" + customerId))
                .body(CommonResponse.onSuccess(customerId));
    }

    @PostMapping(path = "/api/v1/customers/{customerId}/orders")
    public ResponseEntity<CommonResponse> placeOrder(
            @PathVariable final Long customerId,
            @RequestBody final PlaceOrderRequest placeOrderRequest)
    {
        Long orderId = orderService.placeOrder(customerId, placeOrderRequest);
        return ResponseEntity.created(URI.create("/api/v1/customers/"+customerId+"/orders/"+orderId))
                .body(CommonResponse.onSuccess(orderId));
    }

    @GetMapping(path = "/api/v1/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<CommonResponse> getOrder(@PathVariable final Long orderId) {
        OrderDto orderDto = orderService.getOrder(orderId);
        return ResponseEntity.ok(CommonResponse.onSuccess(orderDto));
    }

    @GetMapping(path = "/api/v1/customers/{customerId}/orders")
    public ResponseEntity<CommonResponse> getOrders(@PathVariable final Long customerId) {
        List<OrderDto> orderDtos = orderService.getOrders(customerId);
        return ResponseEntity.ok(CommonResponse.onSuccess(orderDtos));
    }

    @DeleteMapping(path = "/api/v1/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<CommonResponse> cancelOrder(
        @PathVariable final Long orderId
    ) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(CommonResponse.onSuccess(), HttpStatus.NO_CONTENT);
    }
}
