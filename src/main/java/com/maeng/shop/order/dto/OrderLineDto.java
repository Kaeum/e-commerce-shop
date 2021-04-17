package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Category;
import com.maeng.shop.order.domain.Item;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderLine;

public class OrderLineDto {
    private Long id;
    private Item item;
    private Category category;


    public OrderLineDto() {
    }

    public OrderLineDto(Long id, Item item, Category category) {
        this.id = id;
        this.item = item;
        this.category = category;
    }

    public static OrderLineDto toDto(OrderLine orderLine) {
        return new OrderLineDto(orderLine.getId(), orderLine.getItem(), orderLine.getCategory());
    }
}
