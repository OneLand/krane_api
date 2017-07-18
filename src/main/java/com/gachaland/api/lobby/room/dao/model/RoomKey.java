package com.gachaland.api.lobby.room.dao.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jhpark1220 on 2017. 7. 19..
 */
@Data
public class RoomKey implements Serializable {
    private long roomId;
    private long memberId;
}
