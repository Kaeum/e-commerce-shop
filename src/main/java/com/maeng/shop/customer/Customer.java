package com.maeng.shop.customer;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.dto.SignupRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private int age;

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {

    }

    public Customer(String email, String password, int age, String name) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
    }

    public Customer(SignupRequest signupRequest) {
        this(signupRequest.getEmail()
                    ,signupRequest.getPassword()
                    ,signupRequest.getAge()
                    ,signupRequest.getName());
    }

    public Long getId() {
        return id;
    }
}
