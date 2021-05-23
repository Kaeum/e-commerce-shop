package com.maeng.shop.product.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.product.domain.Category;
import com.maeng.shop.product.domain.Sex;
import com.maeng.shop.product.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.maeng.shop.supplier.acceptance.SupplierFixtures.새로운_거래처_등록;
import static com.maeng.shop.supplier.acceptance.SupplierFixtures.요청_거래처_맵_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 상품과 관련된 기능을 담당한다.")
public class ProductAcceptanceTest extends AcceptanceTest {
    private Long supplierId;

    @BeforeEach
    public void setUp() {
        super.setUp();
        Map<String, String> supplier = 요청_거래처_맵_생성("Adidas");
        supplierId = Long.parseLong(String.valueOf(새로운_거래처_등록(supplier).as(CommonResponse.class).getReturnData()));
    }

    @DisplayName("Scenario: 새로운 아이템을 등록한다.")
    @Test
    void registerProduct() {
        // given
        Map<String, String> product = ProductFixtures.요청_상품_맵_생성("Adidas Pants", 90000, Sex.UNISEX, Category.CLOTHES);

        // when
        ExtractableResponse<Response> response = ProductFixtures.상품_등록_요청(product, supplierId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("Scenario: 아이템 목록을 조회한다.")
    @Test
    void getProductsBySupplierId() {
        // given
        Map<String, String> product = ProductFixtures.요청_상품_맵_생성("Adidas Pants", 90000, Sex.UNISEX, Category.CLOTHES);
        ProductFixtures.상품_등록_요청(product, supplierId);

        // when
        ExtractableResponse<Response> response = ProductFixtures.상품_목록_조회(supplierId);
        // then
        List<ProductResponse> items = (List<ProductResponse>) response.body().as(CommonResponse.class).getReturnData();
        assertThat(items.size()).isEqualTo(1);
    }


}