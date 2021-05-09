package com.maeng.shop.supplier.dto;

import com.maeng.shop.supplier.domain.Supplier;

public class RegisterSupplierRequest {

    private String companyName;

    public Supplier toSupplier() {
        return new Supplier(companyName);
    }

    public String getCompanyName() {
        return companyName;
    }
}
