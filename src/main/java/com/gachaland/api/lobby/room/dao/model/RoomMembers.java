package com.gachaland.api.lobby.room.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LandRoomMembers")
@IdClass(RoomKey.class)
public class RoomMembers {

    @Id
    private long roomId;

    @Id
    private long memberId;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.RoomJoinType joinType;

}
