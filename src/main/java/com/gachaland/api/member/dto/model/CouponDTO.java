package com.gachaland.api.member.dto.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chris.j3 on 2017. 6. 20..
 */
@Data
public class CouponDTO implements Serializable {

    private long couponId;      // (StoreItem id)

    private boolean used;       // 쿠폰,이용권 사용 여부

}
