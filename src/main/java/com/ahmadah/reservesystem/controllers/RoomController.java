package com.ahmadah.reservesystem.controllers;

import com.ahmadah.reservesystem.exeption.ResourceNotFoundException;
import com.ahmadah.reservesystem.model.Room;
import com.ahmadah.reservesystem.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {
    private final RoomService roomService;
    @GetMapping("/test")
    public ResponseEntity<Room> test(){
        return ResponseEntity.ok(new Room().builder().id(113123).availability(false).price(10.0d).floor(1).type("Simple").build());
    }
    @GetMapping("/")
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Integer id ){
        try {
            return ResponseEntity.ok(roomService.getRoom(id));
        }catch (ResourceNotFoundException ex){
            return  ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.saveRoom(room));
    }
    @PutMapping("/{id}" )
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id,@RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(id,room));
    }
        @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer id){
        try {
            roomService.removeRoom(id);
            return ResponseEntity.ok("room deleted");
        }catch (ResourceNotFoundException ex){
            return  ResponseEntity.notFound().build();
        }
    }

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
}
