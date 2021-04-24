package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.Customer;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderRepository;
import com.maeng.shop.order.dto.OrderDto;
import com.maeng.shop.order.dto.PlaceOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    public OrderService(CustomerService customerService, OrderRepository orderRepository) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void placeOrder(Long customerId, PlaceOrderRequest placeOrderRequest) {
        Customer customer = customerService.getCustomer(customerId);

        //Order order =  new Order(placeOrderRequest);
    }
}
