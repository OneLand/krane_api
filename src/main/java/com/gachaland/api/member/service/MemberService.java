package com.gachaland.api.member.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberWallet;
import com.gachaland.api.member.dao.repository.MemberRepository;
import com.gachaland.api.member.dao.repository.MemberWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gachaland.api.common.Enumerations.MemberType.GUEST;

/**
 * Created by jhpark1220 on 2017. 6. 7..
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberWalletRepository memberWalletRepository;

    public Member getMemberInfo(long memberId) {
        Member member = memberRepository.findOne(memberId);
        return member;
    }

    public Member getMemberInfo(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber);
        return member;
    }

    public boolean registerGuestMember(String memberType, String phoneNumber) {
        Enumerations.MemberType mType = GUEST;
        try {
            mType = Enumerations.MemberType.valueOf(memberType);
        } catch (Exception ex) {
            mType = GUEST;
        }

        try {
            Member member = new Member();
            member.setPhoneNumber(phoneNumber);
            member.setMemberType(mType);
            member = memberRepository.save(member);

            MemberWallet memberWallet = new MemberWallet();
            memberWallet.setMemberId(member.getId());
            memberWallet.setCoin(0);
            memberWallet.setRuby(0);
            memberWalletRepository.save(memberWallet);

        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

}
