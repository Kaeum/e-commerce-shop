package com.maeng.shop.sales.dto;

import com.maeng.shop.sales.domain.Customer;
import com.maeng.shop.sales.domain.MemberLevel;

public class SignUpRequest {
    private String email;

    private String password;

    private int age;

    private String name;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public static Customer toCustomer(SignUpRequest signupRequest) {
        return new Customer(signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getAge(),
                signupRequest.getName(),
                MemberLevel.BRONZE);
    }
}
