package com.maeng.shop.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> itemIds);

    List<Product> findAllBySupplierId(Long supplierId);
}
