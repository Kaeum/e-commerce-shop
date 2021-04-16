package com.maeng.shop.order;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private int price;

    private String name;
}
