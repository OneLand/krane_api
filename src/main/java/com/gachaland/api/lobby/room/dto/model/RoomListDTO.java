package com.gachaland.api.lobby.room.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDTO {

    private String mode;

    private List<RoomDTO> rooms;

}
