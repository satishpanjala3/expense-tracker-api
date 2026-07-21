package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.AddUserRequest;
import com.satish.expense_tracker.dto.request.UpdateUserRequest;
import com.satish.expense_tracker.dto.response.UserResponse;
import com.satish.expense_tracker.entity.Room;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.entity.UserRole;
import com.satish.expense_tracker.repository.RoomRepository;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.BillingSettingService;
import com.satish.expense_tracker.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoomRepository roomRepository;

    private final BillingSettingService billingSettingService;

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);

    }

    @Override
    public UserResponse addUser(AddUserRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode("user@123")
        );

        user.setRole(UserRole.valueOf(request.getRole()));

        user.setStatus("ACTIVE");

        Room room = roomRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        user.setRoom(room);

        user.setCreatedDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        billingSettingService.createMonthlyShareForUser(savedUser.getId());

        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id,
                                   UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());

        user.setPhone(request.getPhone());

        user.setRole(UserRole.valueOf(request.getRole()));

        user.setStatus(request.getStatus());

        userRepository.save(user);

        return mapToResponse(user);

    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(

                user.getId(),

                user.getName(),

                user.getEmail(),

                user.getPhone(),

                user.getRole().name(),

                user.getStatus(),

                user.getRoom().getName(),

                user.getCreatedDate()

        );

    }

}