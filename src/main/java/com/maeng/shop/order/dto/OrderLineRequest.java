package com.maeng.shop.order.dto;

public class OrderLineRequest {

    private Long productId;

    private int orderPrice;

    private String size;

    public Long getProductId() {
        return productId;
    }

    public String getSize() {
        return size;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
