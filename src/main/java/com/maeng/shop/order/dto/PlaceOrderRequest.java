package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Customer;
import com.maeng.shop.order.domain.OrderLine;
import com.maeng.shop.order.domain.OrderState;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderRequest {
    private Customer customerId;

    private OrderState orderState;

    private List<OrderLineRequest> orderLines;

    public PlaceOrderRequest(Customer customerId, OrderState orderState, List<OrderLineRequest> orderLines) {
        this.customerId = customerId;
        this.orderState = orderState;
        this.orderLines = orderLines;
    }
}
