package com.maeng.shop.order.application;

import com.maeng.shop.customer.Customer;
import com.maeng.shop.order.domain.CustomerRepository;
import com.maeng.shop.order.dto.SignupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long signUp(final SignupRequest signupRequest) {
        Customer customer = new Customer(signupRequest);
        customerRepository.save(customer);
        return customer.getId();
    }
}
