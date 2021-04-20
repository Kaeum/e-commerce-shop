package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Category;
import com.maeng.shop.order.domain.Sex;

public class RegisterItemRequest {
    private String name;
    private int unitPrice;
    private Sex sex;
    private Category category;

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public Sex getSex() {
        return sex;
    }

    public Category getCategory() {
        return category;
    }
}
