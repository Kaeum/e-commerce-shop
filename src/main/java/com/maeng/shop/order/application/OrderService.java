package com.maeng.shop.order.application;

import com.maeng.shop.order.domain.*;
import com.maeng.shop.order.dto.OrderDto;
import com.maeng.shop.order.dto.OrderLineRequest;
import com.maeng.shop.order.dto.PlaceOrderRequest;
import com.maeng.shop.order.repository.ItemRepository;
import com.maeng.shop.order.repository.OrderLineRepository;
import com.maeng.shop.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ItemRepository itemRepository;

    public OrderService(CustomerService customerService, OrderRepository orderRepository, OrderLineRepository orderLineRepository, OrderLineRepository orderLineRepository1, ItemRepository itemRepository) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository1;
        this.itemRepository = itemRepository;
    }

    public List<OrderDto> getOrders(Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        return orders.stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long placeOrder(Long customerId, PlaceOrderRequest placeOrderRequest) {
        Customer customer = customerService.getCustomer(customerId);
        Order order = Order.createOrder(customer);

        orderRepository.save(order);

        List<String> sizes = placeOrderRequest.getOrderLines().stream()
                .map(OrderLineRequest::getSize)
                .collect(Collectors.toList());

        List<Long> itemIds = placeOrderRequest.getOrderLines().stream()
            .map(OrderLineRequest::getItemId)
            .collect(Collectors.toList());

        List<Item> items = itemRepository.findByIdIn(itemIds);

        IntStream.range(0, items.size()).forEach(i -> {
            Item item = items.get(i);
            String size = sizes.get(i);
            orderLineRepository.save(OrderLine.createOrderLine(item, size, order));
        });

        return order.getId();
    }
}
