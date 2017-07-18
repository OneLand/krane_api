package com.gachaland.api.lobby.room.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.Constants;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.model.AuthCheckByAccessToken;
import com.gachaland.api.common.model.UserSession;
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

    @ApiOperation(value = "게임방 목록 All 조회", notes = "게임방 All 목록")
    @AuthCheckByAccessToken
    @RequestMapping(method = RequestMethod.GET, value = "")
    public StandardResponse getGameRooms(
            @RequestAttribute(value = Constants.REQ_ATTR_USER, required = false) UserSession session,
            @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN, required = false) String userToken,
            @RequestHeader(value=Constants.REQ_ATTR_USER_ID, required = false) String userId ) {

        List<RoomListDTO> list = roomService.getGameRoomList();
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), list);
    }

    @ApiOperation(value = "게임방 목록 조회", notes = "게임방 목록")
    @AuthCheckByAccessToken
    @RequestMapping(method = RequestMethod.GET, value = "/{game_mode}")
    public StandardResponse getGameRoomsByMode(
            @RequestAttribute(value = Constants.REQ_ATTR_USER, required = false) UserSession session,
            @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN, required = false) String userToken,
            @RequestHeader(value=Constants.REQ_ATTR_USER_ID, required = false) String userId,
            @ApiParam(value = "게임Mode", required = true) @PathVariable(value = "game_mode") String gameMode) {

//        Enumerations.GameMode mode = Enumerations.GameMode.valueOf(gameMode);
        List<RoomDTO> lists = roomService.getGameRoomList(gameMode);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), lists);
    }

    @ApiOperation(value = "TODO - 게임방 Join", notes = "게임방 조인")
    @AuthCheckByAccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/join/{room_id}")
    public StandardResponse joinGameRoomById(
            @RequestAttribute(value = Constants.REQ_ATTR_USER, required = false) UserSession session,
            @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN, required = false) String userToken,
            @RequestHeader(value=Constants.REQ_ATTR_USER_ID, required = false) String userId,
            @ApiParam(value = "room_id", required = true) @PathVariable(value = "room_id") int roomId) {

        // TODO - join room by room id
        RoomDTO room = roomService.joinedRoom(roomId, session);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), room);
    }

    @ApiOperation(value = "TODO - 게임방 Viewer", notes = "게임방 관전")
    @AuthCheckByAccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/viewer/{room_id}")
    public StandardResponse viewerGameRoomById(
            @RequestAttribute(value = Constants.REQ_ATTR_USER, required = false) UserSession session,
            @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN, required = false) String userToken,
            @RequestHeader(value=Constants.REQ_ATTR_USER_ID, required = false) String userId,
            @ApiParam(value = "room_id", required = true) @PathVariable(value = "room_id") int roomId) {

        // TODO - viewer room by room id
        RoomDTO room = roomService.viewerJoin(roomId, session);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), room);
    }

    @ApiOperation(value = "TODO - 게임방 Exit", notes = "게임방 나가기 ")
    @AuthCheckByAccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/exit/{room_id}")
    public StandardResponse exitGameRoomById(
            @RequestAttribute(value = Constants.REQ_ATTR_USER, required = false) UserSession session,
            @RequestHeader(value=Constants.REQ_ATTR_USER_TOKEN, required = false) String userToken,
            @RequestHeader(value=Constants.REQ_ATTR_USER_ID, required = false) String userId,
            @ApiParam(value = "room_id", required = true) @PathVariable(value = "room_id") int roomId) {

        // TODO - exit room by room id
        RoomDTO room = roomService.exitRoom(roomId, session);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), room);
    }
}
