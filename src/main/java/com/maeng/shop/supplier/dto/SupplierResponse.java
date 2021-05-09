package com.maeng.shop.supplier.dto;

import com.maeng.shop.supplier.domain.Supplier;

public class SupplierResponse {
    private Long id;

    private String companyName;

    public SupplierResponse(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static SupplierResponse toResponse(Supplier supplier) {
        return new SupplierResponse(supplier.getId(), supplier.getCompanyName());
    }
}
