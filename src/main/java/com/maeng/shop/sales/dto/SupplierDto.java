package com.maeng.shop.sales.dto;

public class SupplierDto {
    private Long id;

    private String companyName;

    public SupplierDto(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }
}
