package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Customer;

import java.util.List;

public class PlaceOrderRequest {
    private List<OrderLineRequest> orderLines;

    public PlaceOrderRequest(Customer customerId, List<OrderLineRequest> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLineRequest> getOrderLines() {
        return orderLines;
    }
}
