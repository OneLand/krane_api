package com.gachaland.api.member.dao.repository;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.member.dao.model.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 */
public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
    MemberToken findTopByTokenOrderByIssueDateDesc(String token);
    List<MemberToken> findByToken(String token);
    List<MemberToken> findByTokenAndStatus(String token, Enumerations.TokenStatus status);

    List<MemberToken> findByDeviceId(String deviceId);
    List<MemberToken> findByMemberId(long memberId);

}
