package com.maeng.shop.order.acceptance;

import com.maeng.shop.AcceptanceTest;
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
        Map<String, String> customer = CustomerFixtures.요청_고객_맵_생성("newmember1@email.com", "noone!knows123","20","김철수");

        ExtractableResponse<Response> response = CustomerFixtures.회원가입(customer);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }


}
