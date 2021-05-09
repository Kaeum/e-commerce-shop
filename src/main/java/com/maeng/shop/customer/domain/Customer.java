package com.maeng.shop.customer.domain;

import com.maeng.shop.common.BaseEntity;

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

    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel;

    protected Customer() {}

    public Customer(String email, String password, int age, String name, MemberLevel memberLevel) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
        this.memberLevel = memberLevel;
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

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }
}
