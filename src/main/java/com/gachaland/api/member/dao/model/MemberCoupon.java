package com.gachaland.api.member.dao.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LandMemberCoupon",
        indexes = {@Index(name="idx_member_id", columnList = "memberId", unique = false),
                   @Index(name="idx_coupon_id", columnList = "couponId", unique = true)})
public class MemberCoupon {
    // 유저가 보유중인 무료 이용권 + 쿠폰 내역

    @Id
    @GeneratedValue
    private long id;

    private long memberId;

    private long couponId;      // (StoreItem id)

    private boolean used;       // 쿠폰,이용권 사용 여부

}
