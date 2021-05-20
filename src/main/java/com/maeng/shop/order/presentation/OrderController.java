package com.maeng.shop.order.presentation;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.order.application.OrderService;
import com.maeng.shop.order.dto.OrderResponse;
import com.maeng.shop.order.dto.PlaceOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
    public ResponseEntity<CommonResponse<OrderResponse>> getOrder(@PathVariable final Long orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return ResponseEntity.ok(CommonResponse.onSuccess(orderResponse));
    }

    @GetMapping(path = "/api/v1/customers/{customerId}/orders")
    public ResponseEntity<CommonResponse> getOrders(@PathVariable final Long customerId) {
        List<OrderResponse> orderResponses = orderService.getOrders(customerId);
        return ResponseEntity.ok(CommonResponse.onSuccess(orderResponses));
    }

    @DeleteMapping(path = "/api/v1/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<CommonResponse> cancelOrder(
            @PathVariable final Long orderId
    ) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(CommonResponse.onSuccess(), HttpStatus.NO_CONTENT);
    }
}
