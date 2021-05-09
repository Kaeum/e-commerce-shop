package com.maeng.shop.discount.repository;

import com.maeng.shop.discount.domain.CustomerCouponMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCouponMapping, Long> {

}
