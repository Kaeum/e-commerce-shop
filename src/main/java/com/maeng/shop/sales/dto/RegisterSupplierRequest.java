package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Supplier;

public class RegisterSupplierRequest {

    private String companyName;

    public Supplier toSupplier() {
        return new Supplier(companyName);
    }

    public String getCompanyName() {
        return companyName;
    }
}
