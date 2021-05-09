package com.maeng.shop.order.application;

import com.maeng.shop.customer.application.CustomerService;
import com.maeng.shop.customer.domain.Customer;
import com.maeng.shop.order.domain.Order;
import com.maeng.shop.order.domain.OrderLine;
import com.maeng.shop.order.dto.OrderLineRequest;
import com.maeng.shop.order.dto.OrderResponse;
import com.maeng.shop.order.dto.PlaceOrderRequest;
import com.maeng.shop.order.repository.OrderLineRepository;
import com.maeng.shop.order.repository.OrderRepository;
import com.maeng.shop.product.domain.Item;
import com.maeng.shop.product.repository.ItemRepository;
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

    public List<OrderResponse> getOrders(Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        return orders.stream()
                .map(OrderResponse::toResponse)
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

        List<Integer> orderPrices = placeOrderRequest.getOrderLines().stream()
                .map(OrderLineRequest::getOrderPrice)
                .collect(Collectors.toList());

        List<Long> itemIds = placeOrderRequest.getOrderLines().stream()
            .map(OrderLineRequest::getItemId)
            .collect(Collectors.toList());

        List<Item> items = itemRepository.findByIdIn(itemIds);

        IntStream.range(0, items.size()).forEach(i -> {
            Item item = items.get(i);
            String size = sizes.get(i);
            int orderPrice = orderPrices.get(i);
            orderLineRepository.save(OrderLine.createOrderLine(item, size, orderPrice, order));
        });

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);

        order.cancelOrder();
    }

    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);

        return OrderResponse.toResponse(order);
    }
}
