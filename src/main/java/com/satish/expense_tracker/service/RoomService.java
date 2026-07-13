package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.UpdateRoomRequest;
import com.satish.expense_tracker.dto.response.RoomResponse;

public interface RoomService {

    RoomResponse getCurrentRoom();

    RoomResponse updateRoom(Long id, UpdateRoomRequest request);

}