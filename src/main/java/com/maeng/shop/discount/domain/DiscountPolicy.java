package com.maeng.shop.discount.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class DiscountPolicy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int discountRate;

    private LocalDateTime expiresAt;

    protected DiscountPolicy() {}

    public DiscountPolicy(Long id, int discountRate, LocalDateTime expiresAt) {
        this.id = id;
        this.discountRate = discountRate;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
