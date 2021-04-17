package com.maeng.shop.order;

import com.maeng.shop.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
