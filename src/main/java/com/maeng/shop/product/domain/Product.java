package com.maeng.shop.product.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.discount.domain.DiscountPolicy;
import com.maeng.shop.supplier.domain.Supplier;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
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

    @OneToOne(fetch = FetchType.LAZY)
    private DiscountPolicy discountPolicy;

    public Product() {}

    public Product(String name, int unitPrice, Sex sex, Category category, Supplier supplier) {
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
