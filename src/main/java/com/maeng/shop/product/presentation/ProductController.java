package com.maeng.shop.product.presentation;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.product.application.ProductService;
import com.maeng.shop.product.dto.ProductResponse;
import com.maeng.shop.product.dto.RegisterProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/api/v1/suppliers/{supplierId}/products")
    public ResponseEntity<CommonResponse> registerProduct(
            @PathVariable final Long supplierId,
            @RequestBody final RegisterProductRequest registerProductRequest
    ) {
        Long itemId = productService.registerItem(registerProductRequest, supplierId);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/"+supplierId+"/products/"+itemId))
                .body(CommonResponse.onSuccess(itemId));
    }

    @GetMapping(path = "/api/v1/suppliers/{supplierId}/products")
    public ResponseEntity<CommonResponse> getProductsBySupplierId(@PathVariable final Long supplierId) {
        List<ProductResponse> items = productService.getItemsBySupplierId(supplierId);
        return ResponseEntity.ok(CommonResponse.onSuccess(items));
    }
}
