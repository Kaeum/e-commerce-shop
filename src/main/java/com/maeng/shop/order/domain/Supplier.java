package com.maeng.shop.order.domain;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "supplier")
    private List<Item> items = new ArrayList<>();
}
