package com.maeng.shop.supplier.domain;

import com.maeng.shop.customer.domain.Sex;
import com.maeng.shop.product.domain.Category;
import com.maeng.shop.product.domain.Item;
import org.junit.jupiter.api.Test;

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
