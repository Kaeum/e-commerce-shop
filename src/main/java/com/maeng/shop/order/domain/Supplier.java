package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.dto.RegisterSupplierRequest;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Embedded
    private Items items = new Items();

    public Supplier() {}

    public Supplier(final String companyName) {
        this.companyName = companyName;
    }

    public Supplier(RegisterSupplierRequest registerSupplierRequest) {
        this(registerSupplierRequest.getCompanyName());
    }

    public Long getId() {
        return id;
    }

    public void addItem(Item item) {
        items.addItem(item);
    }

    public void addItem(String name, int unitPrice, Sex sex, Category category) {
        items.addItem(new Item(name, unitPrice, sex, category, this));
    }

    public boolean isSelling(Item item) {
        return items.contains(item);
    }
}
