package com.gachaland.api.member.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jhpark1220 on 2017. 6. 7..
 */
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="LandMember", indexes = {@Index(name="idx_member_phone", columnList = "phoneNumber", unique = true)})
public class Member {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 15)
    private String phoneNumber; // 전화번호

    @Column(length = 10)
    private String postNumber;  // 우편번호

    @Column(length = 255)
    private String address;     // 배송주소


}
