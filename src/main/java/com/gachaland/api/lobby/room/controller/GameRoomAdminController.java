package com.gachaland.api.lobby.room.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.model.AuthCheckByAccessToken;
import com.gachaland.api.lobby.room.controller.body.RoomUpdate;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import com.gachaland.api.lobby.room.dto.model.RoomListDTO;
import com.gachaland.api.lobby.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/lobby/room")
@Api(description = "[ADMIN]게임방 정보", tags = "ADMIN GameRoom Info")
public class GameRoomAdminController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "[ADMIN]게임방 등록하기", notes = "게임방 등록")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public StandardResponse registerGameRoom(
            @ApiParam(value = "게임방", required = true) @RequestBody(required = true) RoomDTO room) {

        Room newRoom = roomService.adminRegisterRoom(room, false);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), newRoom);
    }

    @ApiOperation(value = "[ADMIN]게임방 상태 변경", notes = "게임방 변경")
    @RequestMapping(method = RequestMethod.POST, value = "/{room_id}")
    public StandardResponse changeStatusGameRoom(
            @ApiParam(value = "게임방ID", required = true) @PathVariable(value = "room_id") long roomId,
            @RequestBody(required = false) RoomUpdate roomBody) {

        Room updateRoom = roomService.adminRoomUpdate(roomId, roomBody);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), updateRoom);
    }

}
