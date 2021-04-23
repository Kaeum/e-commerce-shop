package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.dto.ItemDto;

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

    public ItemDto toItemDto() {
        return new ItemDto(id, name, unitPrice, sex, category);
    }
}
