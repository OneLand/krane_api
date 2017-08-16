package com.gachaland.api.member.dto.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 */
@Data
public class MemberDTO implements Serializable {

    private long memberId;

    private Enumerations.MemberType memberType;

    private Enumerations.MemberStatus memberStatus;

    private long coin;

    private long ruby;

    private List<CouponDTO> couponList;

}
