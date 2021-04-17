package com.maeng.shop.customer;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.domain.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
