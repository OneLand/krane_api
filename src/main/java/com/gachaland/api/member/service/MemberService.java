package com.gachaland.api.member.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberHistory;
import com.gachaland.api.member.dao.model.MemberWallet;
import com.gachaland.api.member.dao.repository.MemberHistoryRepository;
import com.gachaland.api.member.dao.repository.MemberRepository;
import com.gachaland.api.member.dao.repository.MemberWalletRepository;
import com.gachaland.api.member.dto.mapper.MemberMapper;
import com.gachaland.api.member.dto.model.MemberDTO;
import com.gachaland.api.member.dto.model.MemberHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

    @Autowired
    private MemberMapper memberMapper;

    public MemberDTO getMemberInfo(long memberId) {
        Member member = memberRepository.findOne(memberId);
        return memberMapper.mappingMemberDTO(member);
    }

    public MemberDTO getMemberInfo(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber);
        return memberMapper.mappingMemberDTO(member);
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

    public void loggingMemberHistory(long memberId, String status) {
        Member member = memberRepository.findOne(memberId);
        Enumerations.MemberHistoryStatus historyStatus = Enumerations.MemberHistoryStatus.END;
        try {
            historyStatus = Enumerations.MemberHistoryStatus.valueOf(status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loggingMemberHistory(member, historyStatus);
    }

    private void loggingMemberHistory(Member member, Enumerations.MemberHistoryStatus status) {
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setIssueDate(new Date());
        memberHistory.setMemberHistoryStatus(status);
        memberHistory.setMemberId(member.getId());
        memberHistory.setPayload("logging");

        memberHistoryRepository.saveAndFlush(memberHistory);
    }

    public MemberHistoryDTO getLoggingMemberHistory(long memberId) {
        List<MemberHistory> histories = memberHistoryRepository.findByMemberId(memberId);
        return memberMapper.mappingMemberHistoryDTO(memberId, histories);
    }

}
