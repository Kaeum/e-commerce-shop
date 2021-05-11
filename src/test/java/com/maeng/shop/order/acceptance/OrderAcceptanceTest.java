package com.maeng.shop.order.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.customer.acceptance.CustomerFixtures;
import com.maeng.shop.order.domain.OrderState;
import com.maeng.shop.order.exception.CannotCancelException;
import com.maeng.shop.product.dto.ProductResponse;
import com.maeng.shop.supplier.acceptance.SupplierFixtures;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static com.maeng.shop.supplier.acceptance.SupplierFixtures.요청_아이템_맵_생성;
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
        customerId = Long.parseLong(String.valueOf(CustomerFixtures.회원가입(customer).as(CommonResponse.class).getReturnData()));

        Map<String, String> supplier = SupplierFixtures.요청_거래처_맵_생성("Nike");
        Long supplierId = Long.parseLong(String.valueOf(SupplierFixtures.새로운_거래처_등록(supplier).as(CommonResponse.class).getReturnData()));

        Map<String, String> itemOne = 요청_아이템_맵_생성("Nike Air Force 1 '07", "130000", "MAN", "SHOES");
        Map<String, String> itemTwo = 요청_아이템_맵_생성("Nike NRG Hoody", "100000", "MAN", "CLOTHES");

        itemOneId = Long.parseLong(String.valueOf(SupplierFixtures.새로운_아이템_등록(supplierId, itemOne).as(CommonResponse.class).getReturnData()));
        itemTwoId = Long.parseLong(String.valueOf(SupplierFixtures.새로운_아이템_등록(supplierId, itemTwo).as(CommonResponse.class).getReturnData()));
    }

    @Test
    @DisplayName("Scenario: 고객은 등록된 품목들을 주문할 수 있다.")
    void placeOrderTest() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "130000","L");
        Map<String, String> orderLineTwo = OrderFixtures.요청_주문품목_맵_생성(itemTwoId, "100000","S");
        Map<String, Object> order = OrderFixtures.요청_주문_맵_생성(orderLineOne, orderLineTwo);

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문하기(customerId, order);
        CommonResponse responseBody = response.as(CommonResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(responseBody.getSuccessOrNot()).isEqualTo("Y");
        assertThat(responseBody.getReturnData()).isEqualTo(1);
    }

    @Test
    @DisplayName("Scenario: 고객이 어떤 주문을 했는지 조회할 수 있다.")
    void getOrdersTest() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "130000", "L");
        Map<String, Object> orderOne = OrderFixtures.요청_주문_맵_생성(orderLineOne);

        Map<String, String> orderLineTwo = OrderFixtures.요청_주문품목_맵_생성(itemTwoId, "100000", "S");
        Map<String, Object> orderTwo = OrderFixtures.요청_주문_맵_생성(orderLineTwo);

        OrderFixtures.주문하기(customerId, orderOne);
        OrderFixtures.주문하기(customerId, orderTwo);

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문_목록_조회(customerId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<ProductResponse> items = (List<ProductResponse>) response.body().as(CommonResponse.class).getReturnData();
        assertThat(items.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Scenario: 고객은 주문을 취소할 수 있다.")
    void cancelOrderTest() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "130000", "L");
        Map<String, Object> orderOne = OrderFixtures.요청_주문_맵_생성(orderLineOne);

        Long orderId = Long.parseLong(String.valueOf(OrderFixtures.주문하기(customerId, orderOne).as(CommonResponse.class).getReturnData()));

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문취소(customerId, orderId);

        // then
        ExtractableResponse<Response> getResponse = OrderFixtures.주문_조회(customerId, orderId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(getResponse.jsonPath().getString("returnData.orderState")).isEqualTo(OrderState.CANCEL.toString());
    }

    @Test
    @DisplayName("Scenario: 고객은 NEW 상태인 주문만 취소할 수 있다.")
    void cancelOrderTest_notWithNewOrder() {
        // given
        Map<String, String> orderLineOne = OrderFixtures.요청_주문품목_맵_생성(itemOneId, "130000", "L");
        Map<String, Object> orderOne = OrderFixtures.요청_주문_맵_생성(orderLineOne);

        Long orderId = Long.parseLong(String.valueOf(OrderFixtures.주문하기(customerId, orderOne).as(CommonResponse.class).getReturnData()));
        OrderFixtures.주문취소(customerId, orderId);

        // when
        ExtractableResponse<Response> response = OrderFixtures.주문취소(customerId, orderId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.as(CommonResponse.class).getReturnMessage()).isEqualTo(CannotCancelException.CANNOT_CANCEL_MESSAGE);
    }
}
