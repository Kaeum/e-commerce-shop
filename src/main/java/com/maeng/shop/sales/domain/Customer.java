package com.maeng.shop.sales.domain;

import com.maeng.shop.common.BaseEntity;
import com.maeng.shop.sales.dto.SignupRequest;

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

    public Customer(SignupRequest signupRequest) {
        this(signupRequest.getEmail()
                    ,signupRequest.getPassword()
                    ,signupRequest.getAge()
                    ,signupRequest.getName()
                    ,MemberLevel.BRONZE
        );
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
