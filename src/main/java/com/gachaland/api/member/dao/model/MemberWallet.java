package com.gachaland.api.member.dao.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by chris.j3 on 2017. 6. 7..
 */
@Data
@Entity
//@Table(name = "LandMemberWallet", indexes = {@Index(name="idx_member_id", columnList = "memberId", unique = true)})
@Table(name = "LandMemberWallet")
public class MemberWallet {
    // 유저가 보유중인 재화 정보 기록

    @Id
    private long memberId;

    private long coin;

    private long ruby;

}
