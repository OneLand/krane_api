package com.gachaland.api.lobby.room.dao.repository;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.lobby.room.dao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByActiveTrueAndVisibleTrue();
    List<Room> findByActiveTrue();
    List<Room> findByVisibleTrue();
    List<Room> findByVisibleTrueAndGameMode(String mode);
}
