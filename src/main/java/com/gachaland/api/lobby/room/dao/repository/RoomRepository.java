package com.gachaland.api.lobby.room.dao.repository;

import com.gachaland.api.lobby.room.dao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 6. 30..
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByActiveTrueAndVisibleTrue();
    List<Room> findByActiveTrue();
    List<Room> findByVisibleTrue();
}
