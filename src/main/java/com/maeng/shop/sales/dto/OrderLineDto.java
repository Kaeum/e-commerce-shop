package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Item;
import com.maeng.shop.sales.domain.OrderLine;

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
