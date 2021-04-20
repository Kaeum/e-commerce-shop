package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.Item;
import com.maeng.shop.order.domain.Supplier;
import com.maeng.shop.order.domain.SupplierRepository;
import com.maeng.shop.order.dto.RegisterItemRequest;
import com.maeng.shop.order.dto.RegisterSupplierRequest;
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
        Supplier supplier = new Supplier(registerSupplierRequest);
        supplierRepository.save(supplier);
        return supplier.getId();
    }

    @Transactional
    public Long registerItem(final RegisterItemRequest registerItemRequest, final Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);
        Item item = new Item(registerItemRequest.getName(),
                registerItemRequest.getUnitPrice(),
                registerItemRequest.getSex(),
                registerItemRequest.getCategory(),
                supplier);

        supplier.addItem(item);

        supplierRepository.flush();
        return item.getId();
    }
}
