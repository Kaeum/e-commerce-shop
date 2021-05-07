package com.maeng.shop.sales.repository;

import com.maeng.shop.sales.domain.CustomerCouponMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCouponMapping, Long> {

}
