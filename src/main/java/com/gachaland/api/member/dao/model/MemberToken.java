package com.gachaland.api.member.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jhpark1220 on 2017. 7. 5..
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LandMemberToken",
        indexes = {@Index(name="idx_token", columnList = "token", unique = true),
                   @Index(name="idx_member_id", columnList = "member_id", unique = true),
                   @Index(name="idx_device_id", columnList = "device_id", unique = true)
})
public class MemberToken implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "member_id")
    private long memberId;
//    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.OsType os;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.TokenStatus status;

    private String token;

    @Column(name = "device_id", length = 255)
    private String deviceId;

    private LocalDateTime issueDate;

}
