package com.gachaland.api.member.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
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

    private LocalDateTime issueDate;

    private String payload;

    @Column(nullable = true)
    private Long gameRoomId;

}
