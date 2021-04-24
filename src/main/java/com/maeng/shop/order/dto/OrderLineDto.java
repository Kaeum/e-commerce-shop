package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Item;
import com.maeng.shop.order.domain.OrderLine;

public class OrderLineDto {
    private Long id;
    private Item item;
    private String size;

    public OrderLineDto() {
    }

    public OrderLineDto(Long id, Item item, String size) {
        this.id = id;
        this.item = item;
        this.size = size;
    }

    public static OrderLineDto toDto(OrderLine orderLine) {
        return new OrderLineDto(orderLine.getId(), orderLine.getItem(), orderLine.getSize());
    }
}
