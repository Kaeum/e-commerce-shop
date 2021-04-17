package com.maeng.shop.order;

import com.maeng.shop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feature: 고객과 관련된 기능을 담당한다.")
public class CustomerAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("Scenario: 회원가입한다.")
    void createCustomer() {
        String email = "newmember1@email.com";
        String password = "no_one_knows123";
        int age = 20;
        String name = "김철수";
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("age", age + "");
        body.put("name", name);

        ExtractableResponse<Response> response = RestAssured
                .given()
                    .log().all()
                    .body(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/customers")
                .then()
                    .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
