package com.maeng.shop.order.dto;

import com.maeng.shop.customer.Customer;
import com.maeng.shop.order.domain.OrderLine;
import com.maeng.shop.order.domain.OrderState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderRequest {
    private Customer customerId;

    private OrderState orderState;

    private List<OrderLine> orderLines = new ArrayList<>();
}
