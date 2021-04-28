package com.maeng.shop.order.acceptance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFixtures {
    private OrderFixtures() {}

    public static Map<String, Object> 요청_주문_맵_생성(Map... maps) {
        Map<String, Object> order = new HashMap<>();

        List<Map<String, String>> orderLines = new ArrayList<>();

        for(Map<String, String> m : maps) {
            orderLines.add(m);
        }

        order.put("orderLines", orderLines);
        return order;
    }

    public static Map<String, String> 요청_주문품목_맵_생성(Long itemId, String size) {
        Map<String, String> itemOne = new HashMap<>();
        itemOne.put("itemId", itemId + "");
        itemOne.put("size", size);
        return itemOne;
    }
}
