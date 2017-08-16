package com.gachaland.api.member.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@ToString(exclude = {"couponList"})
@Table(name="LandMember", indexes = {@Index(name="idx_member_phone", columnList = "phoneNumber", unique = true)})
public class Member implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 15)
    private String phoneNumber; // 전화번호

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.PhoneNumberType phoneNumberType;

    @Column(length = 10)
    private String postNumber;  // 우편번호

    @Column(length = 255)
    private String address;     // 배송주소

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.MemberType memberType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private MemberWallet memberWallet;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="memberId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private List<MemberCoupon> couponList;


}
