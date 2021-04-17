package com.maeng.shop.customer;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.domain.Order;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
