package com.maeng.shop.sales.domain;

import com.maeng.shop.sales.exception.CannotCancelException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void cancelOrder() {
        // given
        Customer customer = new Customer();
        Order order = Order.createOrder(customer);

        // when
        order.cancelOrder();

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.CANCEL);
    }

    @Test
    void cancelOrder_notWithNewOrder() {
        // given
        Customer customer = new Customer();
        Order order = Order.createOrder(customer);
        order.cancelOrder();

        // when
        // then
        assertThatThrownBy( () -> {
            order.cancelOrder();
        }).isInstanceOf(CannotCancelException.class);
    }
}