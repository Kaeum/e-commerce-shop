package com.maeng.shop.order.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.order.domain.Supplier;
import com.maeng.shop.order.dto.SupplierDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.maeng.shop.order.acceptance.SupplierFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplierAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Scenario: 새로운 거래처를 등록한다.")
    void registerSupplierTest() {
        // given
        Map<String, String> supplier = new HashMap<>();
        supplier.put("companyName", "Nike");

        // when
        ExtractableResponse<Response> response = 새로운_거래처_등록(supplier);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    @Test
    @DisplayName("Scenario: 거래처는 판매할 품목들을 등록할 수 있다.")
    void addItemTest() {
        // given
        Map<String, String> supplier = new HashMap<>();
        supplier.put("companyName", "Nike");

        Map<String, String> item = new HashMap<>();
        item.put("name", "Nike Air Force 1 '07");
        item.put("unitPrice", "130000");
        item.put("sex", "MAN");
        item.put("category", "SHOES");

        Long supplierId = 새로운_거래처_등록(supplier).as(Long.class);

        // when
        ExtractableResponse<Response> response = 새로운_아이템_등록(supplierId, item);

        // then
        아이템_등록_확인(response, item, supplierId);
    }
}
