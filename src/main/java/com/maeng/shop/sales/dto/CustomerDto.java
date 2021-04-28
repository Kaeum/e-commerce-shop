package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Customer;

public class CustomerDto {
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

    public CustomerDto() {
    }

    public CustomerDto(Long id, String email, int age, String name) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getEmail(), customer.getAge(), customer.getName());
    }
}
