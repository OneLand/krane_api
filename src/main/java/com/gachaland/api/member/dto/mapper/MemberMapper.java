package com.gachaland.api.member.dto.mapper;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberCoupon;
import com.gachaland.api.member.dto.model.CouponDTO;
import com.gachaland.api.member.dto.model.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris.j3 on 2017. 6. 20..
 */
@Service
public class MemberMapper {

    public MemberDTO mappingMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();

        if (member == null) {
            memberDTO.setMemberStatus(Enumerations.MemberStatus.UNREGISTERED);
        }
        else {
            memberDTO.setMemberId(member.getId());
            memberDTO.setMemberType(member.getMemberType());
            memberDTO.setMemberStatus(Enumerations.MemberStatus.REGISTERED);
            memberDTO.setRuby(member.getMemberWallet().getRuby());
            memberDTO.setCoin(member.getMemberWallet().getCoin());
            memberDTO.setCouponList(mappingCouponDTO(member.getCouponList()));
        }
        return memberDTO;
    }

    private List<CouponDTO> mappingCouponDTO(List<MemberCoupon> coupons) {
        List<CouponDTO> couponDTOs = new ArrayList<>();
        for (MemberCoupon coupon : coupons) {
            CouponDTO couponDTO = new CouponDTO() ;
            couponDTO.setCouponId(coupon.getCouponId());
            couponDTO.setUsed(coupon.isUsed());
            couponDTOs.add(couponDTO);
        }
        return couponDTOs;
    }

}
