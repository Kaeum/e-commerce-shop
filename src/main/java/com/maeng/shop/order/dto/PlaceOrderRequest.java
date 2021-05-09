package com.maeng.shop.order.dto;

import java.util.List;

public class PlaceOrderRequest {
    private List<OrderLineRequest> orderLines;

    public PlaceOrderRequest() {}

    public PlaceOrderRequest(List<OrderLineRequest> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLineRequest> getOrderLines() {
        return orderLines;
    }
}
