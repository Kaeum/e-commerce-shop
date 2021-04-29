package com.maeng.shop.sales.application;

import com.maeng.shop.sales.domain.*;
import com.maeng.shop.sales.dto.OrderDto;
import com.maeng.shop.sales.dto.OrderLineRequest;
import com.maeng.shop.sales.dto.PlaceOrderRequest;
import com.maeng.shop.sales.repository.ItemRepository;
import com.maeng.shop.sales.repository.OrderLineRepository;
import com.maeng.shop.sales.repository.OrderRepository;
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

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);

        order.cancelOrder();
    }
}
