package com.gachaland.api.lobby.room.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private long roomId;

    private String gameMode;

    private String gameModeName;

    private Enumerations.GameRoomType gameRoomType;

    private boolean active;     // 게임방 활성 여부

    private int limit;

    private int viewer;

    private int joiner;

    private String name;

    private String description; // client에 표기될 내용 (곰돌이 푸우 / size : 30 x 30 cm)

}
