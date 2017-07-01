package com.gachaland.api.lobby.room.service;


import com.gachaland.api.common.Enumerations;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dao.repository.RoomRepository;
import com.gachaland.api.lobby.room.dto.mapper.RoomMapper;
import com.gachaland.api.lobby.room.dto.model.RoomDTO;
import com.gachaland.api.member.dao.model.Member;
import com.gachaland.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoomMapper roomMapper;

    // TODO - 게임모드는 자유롭게.. 일단은 String으로 처리 한다
    public Room adminRegisterRoom(RoomDTO roomDTO, boolean immediate) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setGameMode(roomDTO.getGameMode());
        room.setGameModeName(roomDTO.getGameModeName());
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

    public Map<String, List<RoomDTO>> getGameRoomList() {
        Map<String, List<RoomDTO>> listMap = new HashMap<>();
        List<Room> rooms = roomRepository.findByVisibleTrue();

        for (Room room : rooms) {
            if (listMap.containsKey(room.getGameMode())) {
                listMap.get(room.getGameMode()).add(roomMapper.createRoomDTO(room));
            }
            else {
                List<RoomDTO> list = new ArrayList<>();
                list.add(roomMapper.createRoomDTO(room));
                listMap.put(room.getGameMode(), list);
            }
        }
        return listMap;
    }

    // TODO - 게임모드는 자유롭게.. 일단은 String으로 처리 한다
    public List<RoomDTO> getGameRoomList(String mode) {
        List<RoomDTO> list = roomRepository.findByVisibleTrueAndGameMode(mode)
                                            .stream()
                                            .map(room -> roomMapper.createRoomDTO(room))
                                            .collect(Collectors.toList());

        return list;
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
