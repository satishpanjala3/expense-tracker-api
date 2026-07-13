package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.request.UpdateRoomRequest;
import com.satish.expense_tracker.dto.response.RoomResponse;
import com.satish.expense_tracker.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/current")
    public RoomResponse getCurrentRoom() {

        return roomService.getCurrentRoom();

    }

    @PutMapping
    public RoomResponse updateRoom(@RequestBody UpdateRoomRequest request) {

        return roomService.updateRoom(roomService.getCurrentRoom().getId(),request);
    }

}