package com.maeng.shop.sales.application;

import com.maeng.shop.sales.domain.Customer;
import com.maeng.shop.sales.dto.SignupRequest;
import com.maeng.shop.sales.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long signUp(final SignupRequest signupRequest) {
        Customer customer = new Customer(signupRequest);
        customerRepository.save(customer);
        return customer.getId();
    }

    public Customer getCustomer(final Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(RuntimeException::new);
    }
}
