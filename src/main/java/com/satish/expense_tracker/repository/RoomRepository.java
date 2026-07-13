package com.satish.expense_tracker.repository;

import com.satish.expense_tracker.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByName(String name);

    Optional<Room> findFirstBy();

}