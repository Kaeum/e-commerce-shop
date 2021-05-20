package com.maeng.shop.customer.presentation;

import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.customer.application.CustomerService;
import com.maeng.shop.customer.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/api/v1/customers")
    public ResponseEntity<CommonResponse> signUp(@RequestBody final SignUpRequest signupRequest) {
        Long customerId = customerService.signUp(signupRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/customers/" + customerId))
                .body(CommonResponse.onSuccess(customerId));
    }



}
