package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.sales.dto.RegisterSupplierRequest;
import com.maeng.shop.sales.dto.SupplierDto;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

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

    public SupplierDto toDto() {
        return new SupplierDto(id, companyName);
    }
}
