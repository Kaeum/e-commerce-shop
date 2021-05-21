package com.maeng.shop.order.domain;

import com.maeng.shop.customer.domain.Customer;
import com.maeng.shop.customer.domain.MemberLevel;
import com.maeng.shop.order.exception.CannotCancelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        this.customer = new Customer("cust@abc.com", "qlalfqjsgh-123",19, "홍길동", MemberLevel.BRONZE);
    }

    @Test
    void cancelOrder() {
        // given
        Order order = Order.placedBy(customer);

        // when
        order.cancelOrder();

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.CANCEL);
    }

    @Test
    void cancelOrder_notWithNewOrder() {
        // given
        Order order = Order.placedBy(customer);
        order.cancelOrder();

        // when
        // then
        assertThatThrownBy( () -> {
            order.cancelOrder();
        }).isInstanceOf(CannotCancelException.class);
    }
}