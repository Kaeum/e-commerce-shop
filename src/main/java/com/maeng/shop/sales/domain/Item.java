package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.sales.dto.ItemResponse;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int unitPrice;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    public Item() {}

    public Item(String name, int unitPrice, Sex sex, Category category, Supplier supplier) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.sex = sex;
        this.category = category;
        this.supplier = supplier;
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

    public Supplier getSupplier() {
        return supplier;
    }
}
