package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Item;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class OrderLineRequest {

    private Long itemId;

    private String size;

    public Long getItemId() {
        return itemId;
    }

    public String getSize() {
        return size;
    }
}
