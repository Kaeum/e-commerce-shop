package com.maeng.shop.order.dto;

public class OrderLineRequest {

    private Long itemId;

    private int orderPrice;

    private String size;

    public Long getItemId() {
        return itemId;
    }

    public String getSize() {
        return size;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
