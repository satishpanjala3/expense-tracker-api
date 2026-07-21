package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.ChangePasswordRequest;
import com.satish.expense_tracker.dto.request.UpdateProfileRequest;
import com.satish.expense_tracker.dto.response.ProfileResponse;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.service.CurrentUserService;
import com.satish.expense_tracker.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse getProfile() {

        User user = currentUserService.getCurrentUser();

        return new ProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name(),
                user.getStatus(),
                user.getRoom().getName()
        );

    }

    @Override
    public ProfileResponse updateProfile(UpdateProfileRequest request) {

        User user = currentUserService.getCurrentUser();

        user.setName(request.getName());
        user.setPhone(request.getPhone());

        userRepository.save(user);

        return new ProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name(),
                user.getStatus(),
                user.getRoom().getName()
        );

    }

    @Override
    public void changePassword(ChangePasswordRequest request) {

        User user = currentUserService.getCurrentUser();

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

    }

}