package com.maeng.shop.supplier.application;

import com.maeng.shop.product.domain.Product;
import com.maeng.shop.product.dto.ProductResponse;
import com.maeng.shop.product.dto.RegisterProductRequest;
import com.maeng.shop.product.domain.ProductRepository;
import com.maeng.shop.supplier.domain.Supplier;
import com.maeng.shop.supplier.dto.RegisterSupplierRequest;
import com.maeng.shop.supplier.dto.SupplierResponse;
import com.maeng.shop.supplier.domain.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public SupplierService(final SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Long registerSupplier(final RegisterSupplierRequest registerSupplierRequest) {
        Supplier supplier = registerSupplierRequest.toSupplier();
        supplierRepository.save(supplier);
        return supplier.getId();
    }

    @Transactional
    public Long registerItem(final RegisterProductRequest registerProductRequest, final Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);

        Product product = new Product(registerProductRequest.getName(),
                registerProductRequest.getUnitPrice(),
                registerProductRequest.getSex(),
                registerProductRequest.getCategory(),
                supplier);

        productRepository.save(product);
        return product.getId();
    }

    public SupplierResponse getSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(RuntimeException::new);
        return SupplierResponse.toResponse(supplier);
    }

    public List<ProductResponse> getItemsBySupplierId(Long supplierId) {
        List<Product> products = productRepository.findAllBySupplierId(supplierId);

        return products.stream()
                .map(ProductResponse::toResponse)
                .collect(Collectors.toList());
    }
}
