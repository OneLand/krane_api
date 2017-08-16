package com.gachaland.api.lobby.room.dao.repository;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.lobby.room.dao.model.Room;
import com.gachaland.api.lobby.room.dao.model.RoomMembers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomMembersRepository extends JpaRepository<RoomMembers, Long> {
    List<RoomMembers> findByRoomId(long roomId);
    List<RoomMembers> findByRoomIdAndJoinType(long roomId, Enumerations.RoomJoinType type);
    RoomMembers findOneByRoomIdAndMemberId(long roomId, long memberId);

    // 특정 방에 특정 상태 수
    long countByRoomIdAndJoinType(long roomId, Enumerations.RoomJoinType type);
    // 특정 방에 특정 상태가 아닌 수
    long countByRoomIdAndJoinTypeIsNot(long roomId, Enumerations.RoomJoinType type);
}
