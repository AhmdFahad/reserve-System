package com.ahmadah.reservesystem.repositories;

import com.ahmadah.reservesystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositories extends JpaRepository<Room,Integer> {
}
