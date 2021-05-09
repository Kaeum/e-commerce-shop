package com.maeng.shop.product.dto;

import com.maeng.shop.product.domain.Category;
import com.maeng.shop.customer.domain.Sex;

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
