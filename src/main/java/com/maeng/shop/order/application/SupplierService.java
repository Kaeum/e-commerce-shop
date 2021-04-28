package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.Item;
import com.maeng.shop.order.repository.ItemRepository;
import com.maeng.shop.order.domain.Supplier;
import com.maeng.shop.order.repository.SupplierRepository;
import com.maeng.shop.order.dto.ItemDto;
import com.maeng.shop.order.dto.RegisterItemRequest;
import com.maeng.shop.order.dto.RegisterSupplierRequest;
import com.maeng.shop.order.dto.SupplierDto;
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

        itemRepository.save(item);
        return item.getId();
    }

    public SupplierDto getSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);
        return supplier.toDto();
    }

    public List<ItemDto> getItemsBySupplierId(Long supplierId) {
        List<Item> items = itemRepository.findAllBySupplierId(supplierId);

        return items.stream()
                .map(Item::toItemDto)
                .collect(Collectors.toList());
    }
}
