package com.gachaland.api.member.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "LandMemberHistory")
public class MemberHistory {

    @Id
    @GeneratedValue
    private long id;

    private long memberId;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.MemberHistoryStatus memberHistoryStatus;

    private Date issueDate;

    private String payload;

    @Column(nullable = true)
    private Long gameRoomId;

}
