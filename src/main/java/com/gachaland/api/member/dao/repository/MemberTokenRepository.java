package com.gachaland.api.member.dao.repository;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.member.dao.model.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 7. 5..
 */
public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
    List<MemberToken> findByToken(String token);
    List<MemberToken> findByTokenAndStatus(String token, Enumerations.TokenStatus status);

    List<MemberToken> findByDeviceId(String deviceId);
    List<MemberToken> findByMemberId(long memberId);

}
