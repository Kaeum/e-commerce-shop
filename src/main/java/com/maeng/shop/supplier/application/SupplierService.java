package com.maeng.shop.supplier.application;

import com.maeng.shop.supplier.domain.Supplier;
import com.maeng.shop.supplier.domain.SupplierRepository;
import com.maeng.shop.supplier.dto.RegisterSupplierRequest;
import com.maeng.shop.supplier.dto.SupplierResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(final SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Long registerSupplier(final RegisterSupplierRequest registerSupplierRequest) {
        Supplier supplier = registerSupplierRequest.toSupplier();
        supplierRepository.save(supplier);
        return supplier.getId();
    }

    public SupplierResponse getSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);
        return SupplierResponse.toResponse(supplier);
    }
}
