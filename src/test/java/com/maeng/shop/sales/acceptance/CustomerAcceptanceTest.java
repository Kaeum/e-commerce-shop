package com.maeng.shop.sales.acceptance;

import com.maeng.shop.AcceptanceTest;
import com.maeng.shop.common.CommonResponse;
import com.maeng.shop.sales.domain.Customer;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 고객과 관련된 기능을 관리한다.")
public class CustomerAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("Scenario: 회원가입한다.")
    void createCustomer() {
        // given
        Map<String, String> customer = CustomerFixtures.요청_고객_맵_생성("newmember1@email.com", "noone!knows123","20","김철수");

        // when
        ExtractableResponse<Response> response = CustomerFixtures.회원가입(customer);
        CommonResponse commonResponse = response.as(CommonResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(commonResponse.getSuccessOrNot()).isEqualTo("Y");
        assertThat(commonResponse.getReturnData()).isEqualTo(1);
    }
}
