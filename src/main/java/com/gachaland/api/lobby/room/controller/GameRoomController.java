package com.gachaland.api.lobby.room.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import com.gachaland.api.lobby.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
@RestController
@RequestMapping("/lobby/room")
@Api(description = "게임방 정보", tags = "GameRoom Info")
public class GameRoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "게임방 등록하기", notes = "게임방 등록")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public StandardResponse registerGoods(
            @ApiParam(value = "게임방", required = true) @RequestBody(required = true) RoomDTO room) {

        Room newRoom = roomService.adminRegisterRoom(room, false);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), newRoom);
    }

    @ApiOperation(value = "게임방 상태 변경", notes = "게임방 변경")
    @RequestMapping(method = RequestMethod.POST, value = "/{room_id}")
    public StandardResponse registerGoods(
            @ApiParam(value = "게임방ID", required = true) @PathParam(value = "room_id") long roomId,
            @ApiParam(value = "노출여부", required = true) @RequestParam(name = "visible") boolean visible,
            @ApiParam(value = "활성여부", required = true) @RequestParam(name = "active") boolean active) {

        Room updateRoom = roomService.adminRoomUpdate(roomId, active, visible);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), updateRoom);
    }
}
