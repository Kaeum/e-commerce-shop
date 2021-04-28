package com.maeng.shop.sales.repository;

import com.maeng.shop.sales.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdIn(List<Long> itemIds);

    List<Item> findAllBySupplierId(Long supplierId);
}
