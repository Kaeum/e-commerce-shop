package com.maeng.shop.sales.controller;

import com.maeng.shop.sales.application.SupplierService;
import com.maeng.shop.sales.dto.ItemResponse;
import com.maeng.shop.sales.dto.RegisterItemRequest;
import com.maeng.shop.sales.dto.RegisterSupplierRequest;
import com.maeng.shop.sales.dto.SupplierResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(path = "/api/v1/suppliers/{supplierId}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable final Long supplierId) {
        SupplierResponse supplier = supplierService.getSupplier(supplierId);
        return ResponseEntity.ok(supplier);
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
        Long itemId = supplierService.registerItem(registerItemRequest, supplierId);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/"+supplierId+"/items/"+itemId)).body(itemId);
    }

    @GetMapping(path = "/api/v1/suppliers/{supplierId}/items")
    public ResponseEntity<List<ItemResponse>> getItemsBySupplierId(@PathVariable final Long supplierId) {
        return ResponseEntity.ok(supplierService.getItemsBySupplierId(supplierId));
    }
}
