package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderRepository;
import com.maeng.shop.order.dto.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());
    }
}
