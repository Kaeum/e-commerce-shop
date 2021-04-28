package com.maeng.shop.sales.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.sales.dto.OrderDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.maeng.shop.sales.acceptance.SupplierFixtures.요청_아이템_맵_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 주문과 관련된 기능을 관리한다.")
public class OrderAcceptanceTest extends AcceptanceTest {
    private Long customerId;
    private Long itemOneId;
    private Long itemTwoId;

    @BeforeEach
    public void setUp() {
        super.setUp();

        Map<String, String> customer = CustomerFixtures.요청_고객_맵_생성("newmember1@email.com", "noone!knows123","20","김철수");
        customerId = CustomerFixtures.회원가입(customer).as(Long.class);

        Map<String, String> supplier = SupplierFixtures.요청_거래처_맵_생성("Nike");
        Long supplierId = SupplierFixtures.새로운_거래처_등록(supplier).as(Long.class);

        Map<String, String> itemOne = 요청_아이템_맵_생성("Nike Air Force 1 '07", "130000", "MAN", "SHOES");
        Map<String, String> itemTwo = 요청_아이템_맵_생성("Nike NRG Hoody", "100000", "MAN", "CLOTHES");

        itemOneId = SupplierFixtures.새로운_아이템_등록(supplierId, itemOne).as(Long.class);
        itemTwoId = SupplierFixtures.새로운_아이템_등록(supplierId, itemTwo).as(Long.class);
    }

    @Test
    @DisplayName("Scenario: 고객은 등록된 품목들을 주문할 수 있다.")
    void placeOrder() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "L");
        Map<String, String> orderLineTwo = OrderFixtures.요청_주문품목_맵_생성(itemTwoId, "S");
        Map<String, Object> order = OrderFixtures.요청_주문_맵_생성(orderLineOne, orderLineTwo);

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문하기(customerId, order);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.as(Long.class)).isEqualTo(1);
    }

    @Test
    @DisplayName("Scenario: 고객이 어떤 주문을 했는지 조회할 수 있다.")
    void getOrders() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "L");
        Map<String, Object> orderOne = OrderFixtures.요청_주문_맵_생성(orderLineOne);

        Map<String, String> orderLineTwo = OrderFixtures.요청_주문품목_맵_생성(itemTwoId, "S");
        Map<String, Object> orderTwo = OrderFixtures.요청_주문_맵_생성(orderLineOne);

        OrderFixtures.주문하기(customerId, orderOne);
        OrderFixtures.주문하기(customerId, orderTwo);

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문_목록_조회(customerId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getList(".", OrderDto.class).size()).isEqualTo(2);
    }
}