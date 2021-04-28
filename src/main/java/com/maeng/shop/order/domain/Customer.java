package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.order.dto.SignupRequest;

import javax.persistence.*;

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

    protected Customer() {}

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

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
