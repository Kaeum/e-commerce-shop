package com.maeng.shop.supplier.application;

import com.maeng.shop.product.domain.Item;
import com.maeng.shop.product.dto.ItemResponse;
import com.maeng.shop.product.dto.RegisterItemRequest;
import com.maeng.shop.product.repository.ItemRepository;
import com.maeng.shop.supplier.domain.Supplier;
import com.maeng.shop.supplier.dto.RegisterSupplierRequest;
import com.maeng.shop.supplier.dto.SupplierResponse;
import com.maeng.shop.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ItemRepository itemRepository;

    public SupplierService(final SupplierRepository supplierRepository, ItemRepository itemRepository) {
        this.supplierRepository = supplierRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Long registerSupplier(final RegisterSupplierRequest registerSupplierRequest) {
        Supplier supplier = registerSupplierRequest.toSupplier();
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

        itemRepository.save(item);
        return item.getId();
    }

    public SupplierResponse getSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);
        return SupplierResponse.toResponse(supplier);
    }

    public List<ItemResponse> getItemsBySupplierId(Long supplierId) {
        List<Item> items = itemRepository.findAllBySupplierId(supplierId);

        return items.stream()
                .map(ItemResponse::toResponse)
                .collect(Collectors.toList());
    }
}
