package com.maeng.shop.product.application;

import com.maeng.shop.product.domain.Product;
import com.maeng.shop.product.domain.ProductRepository;
import com.maeng.shop.product.dto.ProductResponse;
import com.maeng.shop.product.dto.RegisterProductRequest;
import com.maeng.shop.supplier.domain.Supplier;
import com.maeng.shop.supplier.domain.SupplierRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class ProductService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;


    public ProductService(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
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

    public List<ProductResponse> getItemsBySupplierId(Long supplierId) {
        List<Product> products = productRepository.findAllBySupplierId(supplierId);

        return products.stream()
                .map(ProductResponse::toResponse)
                .collect(Collectors.toList());
    }
}
