package com.gachaland.api.lobby.room.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.exception.GameRoomException;
import com.gachaland.api.lobby.room.dao.model.RoomMembers;
import com.gachaland.api.lobby.room.dao.repository.RoomMembersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class RoomMemberService {

    @Autowired
    private RoomMembersRepository roomMembersRepository;


    public boolean isJoined(long roomId, long memberId) {
        RoomMembers roomMembers = roomMembersRepository.findOneByRoomIdAndMemberId(roomId, memberId);
        if (roomMembers.getJoinType() == Enumerations.RoomJoinType.JOIN)
            return true;
        else
            return false;
    }

    public boolean isViewer(long roomId, long memberId) {
        RoomMembers roomMembers = roomMembersRepository.findOneByRoomIdAndMemberId(roomId, memberId);
        if (roomMembers.getJoinType() == Enumerations.RoomJoinType.VIEW)
            return true;
        else
            return false;
    }

    public boolean isEntered(long roomId, long memberId) {
        RoomMembers roomMembers = roomMembersRepository.findOneByRoomIdAndMemberId(roomId, memberId);
        if (roomMembers.getJoinType() == Enumerations.RoomJoinType.EXIT)
            return false;
        else
            return true;
    }

    @Transactional
    public RoomMembers upsertMemberRoomStatus(long roomId, long memberId, Enumerations.RoomJoinType joinType) {
        RoomMembers roomMembers = new RoomMembers();
        roomMembers.setRoomId(roomId);
        roomMembers.setMemberId(memberId);
        roomMembers.setJoinType(joinType);

        return roomMembersRepository.save(roomMembers);
    }

    public RoomMembers getRoomMemberStatus(long roomId, long memberId) {
        return roomMembersRepository.findOneByRoomIdAndMemberId(roomId, memberId);
    }

}
