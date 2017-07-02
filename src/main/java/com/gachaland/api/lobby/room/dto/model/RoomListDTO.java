package com.gachaland.api.lobby.room.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 7. 2..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDTO {

    private String mode;

    private List<RoomDTO> rooms;

}
