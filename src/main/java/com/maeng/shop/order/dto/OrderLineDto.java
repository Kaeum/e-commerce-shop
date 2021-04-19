package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Item;
import com.maeng.shop.order.domain.OrderLine;

public class OrderLineDto {
    private Long id;
    private Item item;

    public OrderLineDto() {
    }

    public OrderLineDto(Long id, Item item) {
        this.id = id;
        this.item = item;
    }

    public static OrderLineDto toDto(OrderLine orderLine) {
        return new OrderLineDto(orderLine.getId(), orderLine.getItem());
    }
}
