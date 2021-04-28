package com.maeng.shop.order.controller;

import com.maeng.shop.order.application.SupplierService;
import com.maeng.shop.order.dto.ItemDto;
import com.maeng.shop.order.dto.RegisterItemRequest;
import com.maeng.shop.order.dto.RegisterSupplierRequest;
import com.maeng.shop.order.dto.SupplierDto;
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
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable final Long supplierId) {
        SupplierDto supplier = supplierService.getSupplier(supplierId);
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
    public ResponseEntity<List<ItemDto>> getItemsBySupplierId(@PathVariable final Long supplierId) {
        return ResponseEntity.ok(supplierService.getItemsBySupplierId(supplierId));
    }
}
