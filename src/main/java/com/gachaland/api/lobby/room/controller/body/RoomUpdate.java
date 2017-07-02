package com.gachaland.api.lobby.room.controller.body;

import lombok.Data;

/**
 * Created by jhpark1220 on 2017. 7. 2..
 */
@Data
public class RoomUpdate {

    private Boolean visible;

    private Boolean active;

    private String imageUrl;

    private String description;

}
