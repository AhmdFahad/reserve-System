package com.ahmadah.reservesystem.controller;

import com.ahmadah.reservesystem.exeption.ResourceNotFoundException;
import com.ahmadah.reservesystem.model.Room;
import com.ahmadah.reservesystem.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import  com.ahmadah.reservesystem.controllers.RoomController;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void testGetAllRooms() throws Exception {
        // Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, true, "Standard", 1, 100.0));
        rooms.add(new Room(2, false, "Deluxe", 2, 200.0));
        when(roomService.getAll()).thenReturn(rooms);

        // Act & Assert
        mockMvc.perform(get("/api/v1/room/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].availability").value(true))
                .andExpect(jsonPath("$[0].type").value("Standard"))
                .andExpect(jsonPath("$[0].floor").value(1))
                .andExpect(jsonPath("$[0].price").value(100.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].availability").value(false))
                .andExpect(jsonPath("$[1].type").value("Deluxe"))
                .andExpect(jsonPath("$[1].floor").value(2))
                .andExpect(jsonPath("$[1].price").value(200.0));

        verify(roomService, times(1)).getAll();
        verifyNoMoreInteractions(roomService);
    }

    // Add tests for other methods in the RoomController class

}
