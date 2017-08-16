package com.gachaland.api.member.dto.model;

import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class CouponDTO implements Serializable {

    private long couponId;      // (StoreItem id)

    private boolean used;       // 쿠폰,이용권 사용 여부

}
