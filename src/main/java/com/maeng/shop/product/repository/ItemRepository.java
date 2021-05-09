package com.maeng.shop.product.repository;

import com.maeng.shop.product.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdIn(List<Long> itemIds);

    List<Item> findAllBySupplierId(Long supplierId);
}
