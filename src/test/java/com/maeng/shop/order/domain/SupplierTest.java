package com.maeng.shop.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierTest {
    private Supplier supplier;

    @Test
    void addItemTest() {
        // given
        supplier = new Supplier("Nike");
        Item item = new Item("Nike Air Force 1 '07", 130000, Sex.MAN, Category.SHOES, supplier);

        // when
//        supplier.addItem(item);

        // then
        //assertThat(supplier.isSelling(item)).isTrue();
    }
}
