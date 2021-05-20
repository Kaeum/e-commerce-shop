package com.maeng.shop.product.presentation;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.product.application.ProductService;
import com.maeng.shop.product.domain.ProductRepository;
import com.maeng.shop.product.dto.ProductResponse;
import com.maeng.shop.product.dto.RegisterProductRequest;
import com.maeng.shop.supplier.application.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/api/v1/suppliers/{supplierId}/items")
    public ResponseEntity<CommonResponse> registerItem(
            @PathVariable final Long supplierId,
            @RequestBody final RegisterProductRequest registerProductRequest
    ) {
        Long itemId = productService.registerItem(registerProductRequest, supplierId);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/"+supplierId+"/items/"+itemId))
                .body(CommonResponse.onSuccess(itemId));
    }

    @GetMapping(path = "/api/v1/suppliers/{supplierId}/items")
    public ResponseEntity<CommonResponse> getProductsBySupplierId(@PathVariable final Long supplierId) {
        List<ProductResponse> items = productService.getItemsBySupplierId(supplierId);
        return ResponseEntity.ok(CommonResponse.onSuccess(items));
    }
}
