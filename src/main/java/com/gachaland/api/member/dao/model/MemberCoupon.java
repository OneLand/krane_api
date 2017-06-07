package com.gachaland.api.member.dao.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chris.j3 on 2017. 6. 7..
 */
@Data
@Entity
@Table(name = "LandMemberCoupon")
public class MemberCoupon {
    // 유저가 보유중인 무료 이용권 + 쿠폰 내역

    @Id
    private long id;

    private long memberId;

    private long couponId;      // (StoreGoods id)

    private boolean used;       // 쿠폰,이용권 사용 여부



}
