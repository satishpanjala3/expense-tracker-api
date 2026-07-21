package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.response.DashboardResponse;
import com.satish.expense_tracker.entity.Room;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.repository.RoomRepository;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.DashboardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Override
    public DashboardResponse getDashboard() {

        Double totalExpenses = 12500.65;

        Double currentMonthExpenses = 1300.0;

        long totalMembers = userRepository.count();

        long activeMembers =
                userRepository.countByStatus("ACTIVE");

        long pendingMembers =
                userRepository.countByStatus("PENDING");

        Room room = roomRepository.findFirstBy().orElse(null);

        String roomName = room != null
                ? room.getName()
                : "";

        return new DashboardResponse(

                roomName,

                totalMembers,

                activeMembers,

                pendingMembers,

                totalExpenses,

                currentMonthExpenses
        );

    }

}