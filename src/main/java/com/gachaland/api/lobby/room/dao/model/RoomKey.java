package com.gachaland.api.lobby.room.dao.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomKey implements Serializable {
    private long roomId;
    private long memberId;
}
