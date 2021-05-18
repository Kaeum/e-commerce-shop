package com.maeng.shop.product.dto;

import com.maeng.shop.product.domain.Category;
import com.maeng.shop.product.domain.Product;
import com.maeng.shop.product.domain.Sex;

public class ProductResponse {
    private Long id;

    private String name;

    private int unitPrice;

    private Sex sex;

    private Category category;

    public ProductResponse(Long id, String name, int unitPrice, Sex sex, Category category) {
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

    public static ProductResponse toResponse(final Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getUnitPrice(), product.getSex(), product.getCategory());
    }
}
