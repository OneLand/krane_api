package com.gachaland.api.member.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LandMemberPost",
        indexes = @Index(name = "idx_message_member", columnList = "id, member_id", unique = true)
)
/**
 * 우편함
 */
public class MemberPost {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "member_id")
    private long memberId;

    @Column(length = 255)
    private String imageUrl;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String contents;

    private boolean reading = false;

    private boolean displayable = false;    // 유저 삭제 (DB에는 남겨둠)

    private LocalDateTime issueDt;       // 발송 일

    private LocalDateTime readDt;        // 읽은 날

}
