package com.maeng.shop.order.dto;

import com.maeng.shop.order.domain.Items;
import com.maeng.shop.order.domain.Supplier;

import javax.persistence.Embedded;
import java.util.List;

public class SupplierDto {
    private Long id;

    private String companyName;

    private List<ItemDto> items;

    public SupplierDto(Long id, String companyName, List<ItemDto> items) {
        this.id = id;
        this.companyName = companyName;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<ItemDto> getItems() {
        return items;
    }
}
