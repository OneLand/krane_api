package com.gachaland.api.lobby.room.service;


import com.gachaland.api.common.Enumerations;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dao.repository.RoomRepository;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberService memberService;

    public Room adminRegisterRoom(RoomDTO roomDTO, boolean immediate) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setDescription(roomDTO.getDescription());
        room.setGameRoomType(roomDTO.getGameRoomType());
        room.setActive(false);
        room.setVisible(immediate);
        room.setLimitUserCount(roomDTO.getLimit());
        room.setJoinerCount(0);
        room.setViewerCount(0);

        roomRepository.save(room);
        return room;
    }

    public Room adminRoomUpdate(long roomId, boolean active, boolean visible) {
        Room room = roomRepository.findOne(roomId);
        if (room == null)
            return null;

        room.setActive(active);
        room.setVisible(visible);
        roomRepository.save(room);
        return room;
    }

    private Room joinedRoom(long roomId, Member member) {
        Room room = roomRepository.findOne(roomId);
        if (room == null)
            return null;

        room.increaseJoiner();
        roomRepository.save(room);

        memberService.loggingMemberGameHistory(member, Enumerations.MemberHistoryStatus.ROOM_JOIN, roomId, "");
        return room;
    }

    private Room exitRoom(long roomId, Member member) {
        Room room = roomRepository.findOne(roomId);
        if (room == null)
            return null;

        room.decreaseJoiner();
        roomRepository.save(room);

        memberService.loggingMemberGameHistory(member, Enumerations.MemberHistoryStatus.ROOM_EXIT, roomId, "");
        return room;
    }

    private Room viewerJoin(long roomId, Member member) {
        Room room = roomRepository.findOne(roomId);
        if (room == null)
            return null;

        room.increaseViewer();
        roomRepository.save(room);

        return room;
    }

    private Room viewerExit(long roomId, Member member) {
        Room room = roomRepository.findOne(roomId);
        if (room == null)
            return null;

        room.decreaseViewer();
        roomRepository.save(room);

        return room;
    }

}
