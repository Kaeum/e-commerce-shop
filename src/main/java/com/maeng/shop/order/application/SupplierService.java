package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.Supplier;
import com.maeng.shop.order.domain.SupplierRepository;
import com.maeng.shop.order.dto.RegisterSupplierRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Long registerSupplier(RegisterSupplierRequest registerSupplierRequest) {
        Supplier supplier = new Supplier(registerSupplierRequest);
        supplierRepository.save(supplier);
        return supplier.getId();
    }
}
