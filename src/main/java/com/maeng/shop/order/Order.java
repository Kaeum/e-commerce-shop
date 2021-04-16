package com.maeng.shop.order;

import com.maeng.shop.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();


}
