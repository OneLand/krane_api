package com.gachaland.api.lobby.room.dto.mapper;

import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import org.springframework.stereotype.Service;

/**
 * Created by jhpark1220 on 2017. 7. 1..
 */
@Service
public class RoomMapper {

    public RoomDTO createRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getId());
        roomDTO.setGameMode(room.getGameMode());
        roomDTO.setGameModeName(room.getGameModeName());
        roomDTO.setImageUrl(room.getImageUrl());
        roomDTO.setGameRoomType(room.getGameRoomType());
        roomDTO.setName(room.getName());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setActive(room.isActive());
        roomDTO.setLimit(room.getLimitUserCount());
        roomDTO.setJoiner(room.getJoinerCount());
        roomDTO.setViewer(room.getViewerCount());

        return roomDTO;
    }
}
