package com.gachaland.api.lobby.room.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.lobby.room.controller.body.RoomUpdate;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import com.gachaland.api.lobby.room.dto.model.RoomListDTO;
import com.gachaland.api.lobby.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public StandardResponse registerGameRoom(
            @ApiParam(value = "게임방", required = true) @RequestBody(required = true) RoomDTO room) {

        Room newRoom = roomService.adminRegisterRoom(room, false);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), newRoom);
    }

    @ApiOperation(value = "게임방 상태 변경", notes = "게임방 변경")
    @RequestMapping(method = RequestMethod.POST, value = "/{room_id}")
    public StandardResponse changeStatusGameRoom(
            @ApiParam(value = "게임방ID", required = true) @PathVariable(value = "room_id") long roomId,
            @RequestBody(required = false) RoomUpdate roomBody) {

//            @ApiParam(value = "게임방 이미지", required = false) @RequestParam(name = "image", required = false) String imageUrl,
//            @ApiParam(value = "게임방 설명", required = false) @RequestParam(name = "description", required = false) String description,
//            @ApiParam(value = "노출여부", required = false) @RequestParam(name = "visible", required = false) Boolean visible,
//            @ApiParam(value = "활성여부", required = false) @RequestParam(name = "active", required = false) Boolean active) {

//        Room updateRoom = roomService.adminRoomUpdate(roomId, active, visible, description, imageUrl);
        Room updateRoom = roomService.adminRoomUpdate(roomId, roomBody);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), updateRoom);
    }

    @ApiOperation(value = "게임방 목록 All 조회", notes = "게임방 All 목록")
    @RequestMapping(method = RequestMethod.GET, value = "")
    public StandardResponse getGameRooms() {

        List<RoomListDTO> list = roomService.getGameRoomList();
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), list);
    }

    @ApiOperation(value = "게임방 목록 조회", notes = "게임방 목록")
    @RequestMapping(method = RequestMethod.GET, value = "/{game_mode}")
    public StandardResponse getGameRoomsByMode(
            @ApiParam(value = "게임Mode", required = true) @PathVariable(value = "game_mode") String gameMode) {

//        Enumerations.GameMode mode = Enumerations.GameMode.valueOf(gameMode);
        List<RoomDTO> lists = roomService.getGameRoomList(gameMode);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), lists);
    }
}