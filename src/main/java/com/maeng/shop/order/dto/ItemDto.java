package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Category;
import com.maeng.shop.order.domain.Sex;

public class ItemDto {
    private Long id;

    private String name;

    private int unitPrice;

    private Sex sex;

    private Category category;

    public ItemDto(Long id, String name, int unitPrice, Sex sex, Category category) {
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

}
