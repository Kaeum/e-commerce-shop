package com.maeng.shop.sales.repository;

import com.maeng.shop.sales.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
