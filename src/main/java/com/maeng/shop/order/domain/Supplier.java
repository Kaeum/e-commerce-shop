package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.dto.RegisterSupplierRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @OneToMany(mappedBy = "supplier")
    private List<Item> items = new ArrayList<>();

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
}
