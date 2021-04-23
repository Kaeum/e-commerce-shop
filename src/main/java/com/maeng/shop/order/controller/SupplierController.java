package com.maeng.shop.order.controller;

import com.maeng.shop.order.application.SupplierService;
import com.maeng.shop.order.dto.RegisterItemRequest;
import com.maeng.shop.order.dto.RegisterSupplierRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(path = "/api/v1/suppliers")
    public ResponseEntity<Long> registerSupplier(@RequestBody final RegisterSupplierRequest registerSupplierRequest) {
        Long supplierId = supplierService.registerSupplier(registerSupplierRequest);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/" + supplierId))
                .body(supplierId);
    }

    @PostMapping(path = "/api/v1/suppliers/{supplierId}/items")
    public ResponseEntity<Long> registerItem(
            @PathVariable final Long supplierId,
            @RequestBody final RegisterItemRequest registerItemRequest
    ) {
        supplierService.registerItem(registerItemRequest, supplierId);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/"+supplierId+"/items/")).build();
    }
}
