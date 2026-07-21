package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.UpdateRoomRequest;
import com.satish.expense_tracker.dto.response.RoomResponse;
import com.satish.expense_tracker.entity.Room;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.repository.RoomRepository;
import com.satish.expense_tracker.service.CurrentUserService;
import com.satish.expense_tracker.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final CurrentUserService currentUserService;

    @Override
    public RoomResponse getCurrentRoom() {

        User currentUser = currentUserService.getCurrentUser();

        Room room = currentUser.getRoom();

        return new RoomResponse(
                room.getId(),
                room.getName()
        );
    }

    @Override
    public RoomResponse updateRoom(Long id, UpdateRoomRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        if (currentUser.getRole() != com.satish.expense_tracker.entity.UserRole.ADMIN) {
            throw new RuntimeException("Only admin can update room");
        }

        Room room = currentUser.getRoom();

        room.setName(request.getName());

        roomRepository.save(room);

        return new RoomResponse(
                room.getId(),
                room.getName()
        );
    }
}