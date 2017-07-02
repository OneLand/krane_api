package com.gachaland.api.member.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jhpark1220 on 2017. 7. 2..
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

    private long memberId;

    @Column(length = 255)
    private String imageUrl;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String contents;

    private boolean checked;

    private Date issueDt;

}
