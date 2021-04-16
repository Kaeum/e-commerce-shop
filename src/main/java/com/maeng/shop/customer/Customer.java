package com.maeng.shop.customer;

import com.maeng.shop.order.Order;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
