package com.maeng.shop.discount.domain;

import com.maeng.shop.customer.domain.MemberLevel;
import com.maeng.shop.discount.exception.InappropriateMemberLevelException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CouponTest {
    private Coupon coupon;

    @Test
    void checkMemberLevelTest() {
        // given
        coupon = new MemberLevelCoupon("실버회원 쿠폰", 7, 15000, MemberLevel.SILVER);

        // when
        // then
        assertThatThrownBy(() -> {
            coupon.checkMemberLevel(MemberLevel.BRONZE);
        }).isInstanceOf(InappropriateMemberLevelException.class);
    }

}
