package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Category;
import com.maeng.shop.sales.domain.Item;
import com.maeng.shop.sales.domain.Sex;

public class ItemResponse {
    private Long id;

    private String name;

    private int unitPrice;

    private Sex sex;

    private Category category;

    public ItemResponse(Long id, String name, int unitPrice, Sex sex, Category category) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.sex = sex;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

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

    public static ItemResponse toResponse(final Item item) {
        return new ItemResponse(item.getId(), item.getName(), item.getUnitPrice(), item.getSex(), item.getCategory());
    }
}
