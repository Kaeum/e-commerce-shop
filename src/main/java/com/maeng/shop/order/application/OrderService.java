package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.*;
import com.maeng.shop.order.dto.OrderDto;
import com.maeng.shop.order.dto.OrderLineRequest;
import com.maeng.shop.order.dto.PlaceOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderService(CustomerService customerService, OrderRepository orderRepository, OrderLineRepository orderLineRepository, ItemRepository itemRepository) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
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

        List<String> sizes = placeOrderRequest.getOrderLines().stream()
                .map(OrderLineRequest::getSize)
                .collect(Collectors.toList());

        List<Long> itemIds = placeOrderRequest.getOrderLines().stream()
            .map(OrderLineRequest::getItemId)
            .collect(Collectors.toList());

        List<Item> items = itemRepository.findByIdIn(itemIds);

        List<OrderLine> orderLines = new ArrayList<>();

        for(int i = 0; i < items.size(); i++) {
            orderLines.add(new OrderLine(items.get(i), sizes.get(i)));
        }

        customer.placeOrder(orderLines);

    }
}
