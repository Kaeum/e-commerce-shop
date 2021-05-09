package com.maeng.shop.customer.dto;

import com.maeng.shop.customer.domain.Customer;

public class CustomerResponse {
    private Long id;

    private String email;

    private int age;

    private String name;

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

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String email, int age, String name) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getAge(), customer.getName());
    }
}
