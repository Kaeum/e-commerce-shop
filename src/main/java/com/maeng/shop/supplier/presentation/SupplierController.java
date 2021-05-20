package com.maeng.shop.supplier.presentation;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.supplier.application.SupplierService;
import com.maeng.shop.supplier.dto.RegisterSupplierRequest;
import com.maeng.shop.supplier.dto.SupplierResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<CommonResponse> registerSupplier(@RequestBody final RegisterSupplierRequest registerSupplierRequest) {
        Long supplierId = supplierService.registerSupplier(registerSupplierRequest);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/" + supplierId))
                .body(CommonResponse.onSuccess(supplierId));
    }
}
