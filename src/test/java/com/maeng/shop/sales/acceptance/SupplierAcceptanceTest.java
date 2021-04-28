package com.maeng.shop.sales.acceptance;

import com.maeng.shop.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.maeng.shop.sales.acceptance.SupplierFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 거래처와 관련된 기능을 관리한다.")
public class SupplierAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Scenario: 새로운 거래처를 등록한다.")
    void registerSupplierTest() {
        // given
        Map<String, String> supplier = 요청_거래처_맵_생성("Nike");

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
        Map<String, String> supplier = 요청_거래처_맵_생성( "Nike");
        Map<String, String> item = 요청_아이템_맵_생성("Nike Air Force 1 '07", "130000", "MAN", "SHOES");
        Long supplierId = 새로운_거래처_등록(supplier).as(Long.class);

        // when
        ExtractableResponse<Response> response = 새로운_아이템_등록(supplierId, item);

        // then
        아이템_등록_확인(response, item, supplierId);
    }


}
