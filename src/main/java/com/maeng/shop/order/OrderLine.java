package com.maeng.shop.order;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue
    @Column(name = "order_line_id")
    private Long id;

    @OneToOne
    private Item item;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;


}
