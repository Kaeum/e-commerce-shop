package com.maeng.shop.product.acceptance;

import com.maeng.shop.product.domain.Category;
import com.maeng.shop.product.domain.Sex;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ProductFixtures {
    public static Map<String, String> 요청_상품_맵_생성(String name, int price, Sex sex, Category category) {
        Map<String, String> product = new HashMap<>();
        product.put("name", name);
        product.put("unitPrice", price + "");
        product.put("sex", sex + "");
        product.put("category", category + "");
        return product;
    }

    public static ExtractableResponse<Response> 상품_등록_요청(Map<String, String> product, Long supplierId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(product)
                .when()
                .post("/api/v1/suppliers/" + supplierId + "/products")
                .then()
                .log()
                .all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_목록_조회(Long supplierId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/suppliers/" + supplierId + "/products")
                .then()
                .log()
                .all()
                .extract();
    }
}
