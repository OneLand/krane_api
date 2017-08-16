package com.gachaland.api.lobby.room.controller.body;

import lombok.Data;

@Data
public class RoomUpdate {

    private Boolean visible;

    private Boolean active;

    private String imageUrl;

    private String description;

}
