package com.gachaland.api.lobby.room.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
@Data
@Entity
@Table(name = "LandLobbyRoom")
public class Room {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 64)
    private String name;    // 운영용

    @Column(length = 64)
    private String gameMode;

    @Column(length = 64)
    private String gameModeName;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.GameRoomType gameRoomType;

    @Column(columnDefinition = "BOOLEAN default 0", nullable = false)
    private boolean visible;     // 게임방 노출 여부

    @Column(columnDefinition = "BOOLEAN default 0", nullable = false)
    private boolean active;     // 게임방 활성 여부

    @Column(columnDefinition = "int default 50", nullable = false)
    private int limitUserCount;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int viewerCount;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int joinerCount;

    @Column(length = 255)
    private String description; // client에 표기될 내용 (곰돌이 푸우 / size : 30 x 30 cm)


    public int increaseJoiner() {
        this.joinerCount++;
        return this.joinerCount;
    }

    public int decreaseJoiner() {
        this.joinerCount--;
        return this.joinerCount;
    }

    public int increaseViewer() {
        this.viewerCount++;
        return this.viewerCount;
    }

    public int decreaseViewer() {
        this.viewerCount--;
        return this.viewerCount;
    }
}
