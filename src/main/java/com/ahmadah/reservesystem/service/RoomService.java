package com.ahmadah.reservesystem.service;

import com.ahmadah.reservesystem.exeption.ResourceNotFoundException;
import com.ahmadah.reservesystem.model.Room;
import com.ahmadah.reservesystem.repositories.RoomRepositories;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepositories roomRepositories;

    public RoomService(RoomRepositories roomRepositories) {
        this.roomRepositories = roomRepositories;
    }
    public List<Room> getAll() {
        return roomRepositories.findAll();
    }
    public Room saveRoom(Room room) {
        return roomRepositories.save(room);
    }
    public void removeRoom(Integer id) throws ResourceNotFoundException{
        roomRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id: " + id));
         roomRepositories.deleteById(id);
    }

    @SneakyThrows
    public Room updateRoom(Integer id, Room room) {
        Room r = roomRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id: " + id));
        r.setFloor(room.getFloor());
        r.setAvailability(room.getAvailability());
        r.setPrice(room.getPrice());
        r.setType(room.getType());
        return roomRepositories.save(r);
    }

    public Room getRoom(Integer id) throws ResourceNotFoundException{
        return roomRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with id: " + id));
    }
}
