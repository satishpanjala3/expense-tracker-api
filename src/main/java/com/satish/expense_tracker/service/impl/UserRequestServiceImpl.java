package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.response.UserRequestResponse;
import com.satish.expense_tracker.entity.Expense;
import com.satish.expense_tracker.entity.RequestStatus;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.repository.ExpenseRepository;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestServiceImpl implements UserRequestService {

    private final UserRepository userRepository;

    private final ExpenseRepository expenseRepository;

    @Override
    public List<UserRequestResponse> getPendingRequests() {
        return userRepository
                .findByRequestStatus(RequestStatus.PENDING)
                .stream()
                .map(user -> UserRequestResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .role(user.getRole().name())
                        .status(user.getStatus())
                        .requestDate(
                                user.getCreatedDate().toString())
                        .build())
                .toList();

    }

    @Override
    public void approveRequest(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow();

        user.setRequestStatus(RequestStatus.APPROVED);

        userRepository.save(user);

    }

    @Override
    public void rejectRequest(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow();

        user.setRequestStatus(RequestStatus.REJECTED);

        userRepository.save(user);

    }

}