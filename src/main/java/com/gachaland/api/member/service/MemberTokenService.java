package com.gachaland.api.member.service;

import com.gachaland.api.common.model.UserSession;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.dao.model.MemberToken;
import com.gachaland.api.member.dao.repository.MemberRepository;
import com.gachaland.api.member.dao.repository.MemberTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 7. 6..
 */
@Service
@Primary
@Slf4j
public class MemberTokenService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberTokenRepository memberTokenRepository;

    @Transactional
    public UserSession checkMe(String userToken, String memberIdStr) {
        long memberId = Long.parseLong(memberIdStr);

        log.info("casting memberId : {} ", memberId);

        Member member = memberRepository.findOne(memberId);
        MemberToken token = memberTokenRepository.findTopByTokenOrderByIssueDateDesc(userToken);

        log.info("query token : {} / {}", userToken, token.toString());

        if (member == null || token == null)
            return null;

        UserSession session = new UserSession();
        session.setMember(member);
        session.setToken(token);
        session.setValid(true);
        return session;
    }

    @Transactional
    public UserSession checkMeDebug(String memberIdStr) {
        long memberId = Long.parseLong(memberIdStr);

        Member member = memberRepository.findOne(memberId);
        List<MemberToken> tokens = memberTokenRepository.findByMemberId(memberId);

        if (member == null || tokens == null || tokens.size() == 0)
            return null;

        UserSession session = new UserSession();
        session.setMember(member);
        session.setToken(tokens.get(0));
        session.setValid(true);
        return session;
    }


}
