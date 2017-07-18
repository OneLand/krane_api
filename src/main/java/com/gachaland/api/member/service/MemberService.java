package com.gachaland.api.member.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.common.model.UserSession;
import com.gachaland.api.common.utils.DateUtils;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberHistory;
import com.gachaland.api.member.dao.model.MemberToken;
import com.gachaland.api.member.dao.model.MemberWallet;
import com.gachaland.api.member.dao.repository.MemberHistoryRepository;
import com.gachaland.api.member.dao.repository.MemberRepository;
import com.gachaland.api.member.dao.repository.MemberTokenRepository;
import com.gachaland.api.member.dao.repository.MemberWalletRepository;
import com.gachaland.api.member.dto.mapper.MemberMapper;
import com.gachaland.api.member.dto.model.MemberDTO;
import com.gachaland.api.member.dto.model.MemberHistoryDTO;
import com.gachaland.api.member.dto.model.RegisterBody;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private MemberTokenRepository memberTokenRepository;

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

    public MemberDTO convertMemberDTO(Member member) {
        return memberMapper.mappingMemberDTO(member);
    }

    @Transactional
    public MemberToken registerGuestMember(RegisterBody registerBody) {
        if (registerBody == null) {
            return null;
        }

        Member member = new Member();
        member.setMemberType(GUEST);

        if (!StringUtils.isEmpty(registerBody.getPhoneNumber())) {
            member.setPhoneNumber(registerBody.getPhoneNumber());
            if (registerBody.isDeviceNumber()) {
                member.setMemberType(Enumerations.MemberType.MEMBER);
            }
            member = memberRepository.save(member);
        }

        MemberWallet memberWallet = new MemberWallet();
        memberWallet.setMemberId(member.getId());
        memberWallet.setCoin(0);
        memberWallet.setRuby(0);
        memberWalletRepository.save(memberWallet);

        MemberToken memberToken = new MemberToken();
        memberToken.setMemberId(member.getId());
        memberToken.setDeviceId(registerBody.getAdid());
        memberToken.setIssueDate(LocalDateTime.now());
        memberToken.setOs(registerBody.getOsType());

        String newToken = generateRefreshToken(member, registerBody.getAdid());
        if (StringUtils.isEmpty(newToken)) {
            memberToken.setStatus(Enumerations.TokenStatus.INVALID);
        }
        else {
            memberToken.setStatus(Enumerations.TokenStatus.VALID);
            memberToken.setToken(newToken);
        }

        memberTokenRepository.save(memberToken);
        return memberToken;
    }

    private String generateRefreshToken(Member member, String udid) {

        String number = member.getPhoneNumber();
        if (StringUtils.isEmpty(number)) {
            number = "guest_number";
        }

        String pwd = String.format("%d-%s_%s:%s", member.getId(), number, udid, LocalDateTime.now().toString());
        String token = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("Digest(in hex format):: " + hexString.toString());
            token = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return token;
    }

    public boolean memberLogin(UserSession session, RegisterBody registerBody) {
        if (session == null || session.isValid() == false)
            return false;

        MemberToken token = upsertMemberToken(session, registerBody);
        session.setToken(token);

        Enumerations.MemberHistoryStatus status = Enumerations.MemberHistoryStatus.LOGIN;
        loggingMemberHistory(session.getMember(),
                status,
                Long.toString(session.getToken().getId()));

        return true;
    }

    private MemberToken upsertMemberToken(UserSession session, RegisterBody registerBody) {

        LocalDateTime current = LocalDateTime.now();
        boolean refresh = false;

        MemberToken memberToken = session.getToken();
        if (!memberToken.getDeviceId().equals(registerBody.getAdid())) {
            memberToken.setDeviceId(registerBody.getAdid());
            refresh = true;
        }

        long diffTm = DateUtils.getTimestampOnTimezone(current) - DateUtils.getTimestampOnTimezone(memberToken.getIssueDate());
        // 12시간 지나면 토큰 갱신
        if (diffTm > (1000 * 60 * 60 * 12)) {
            refresh = true;

            String newToken = generateRefreshToken(session.getMember(), registerBody.getAdid());
            if (!StringUtils.isEmpty(newToken)) {
                memberToken.setStatus(Enumerations.TokenStatus.VALID);
                memberToken.setToken(newToken);
            }
        }

        if (refresh) {
            memberToken.setOs(registerBody.getOsType());

            memberTokenRepository.save(memberToken);
        }
        return memberToken;
    }


    public void loggingMemberHistory(long memberId, String status) {
        Member member = memberRepository.findOne(memberId);
        Enumerations.MemberHistoryStatus historyStatus = Enumerations.MemberHistoryStatus.END;
        try {
            historyStatus = Enumerations.MemberHistoryStatus.valueOf(status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loggingMemberHistory(member, historyStatus, "logging");
    }

    private void loggingMemberHistory(Member member, Enumerations.MemberHistoryStatus status, String payload) {
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setIssueDate(LocalDateTime.now());
        memberHistory.setMemberHistoryStatus(status);
        memberHistory.setMemberId(member.getId());
        memberHistory.setPayload(payload);

        memberHistoryRepository.save(memberHistory);
    }

    public void loggingMemberGameHistory(Member member, Enumerations.MemberHistoryStatus status, long roomId, String payload) {
        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setIssueDate(LocalDateTime.now());
        memberHistory.setMemberHistoryStatus(status);
        memberHistory.setMemberId(member.getId());
        memberHistory.setPayload(payload);
        memberHistory.setGameRoomId(roomId);

        memberHistoryRepository.save(memberHistory);
    }

    public MemberHistoryDTO getLoggingMemberHistory(long memberId) {
        List<MemberHistory> histories = memberHistoryRepository.findByMemberId(memberId);
        return memberMapper.mappingMemberHistoryDTO(memberId, histories);
    }

}
