package com.satish.expense_tracker.controller;

import com.satish.expense_tracker.dto.request.ChangePasswordRequest;
import com.satish.expense_tracker.dto.request.UpdateProfileRequest;
import com.satish.expense_tracker.dto.response.ApiResponse;
import com.satish.expense_tracker.dto.response.ProfileResponse;
import com.satish.expense_tracker.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://expense-tracker-gamma-rouge.vercel.app")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ProfileResponse getProfile() {
        return profileService.getProfile();

    }

    @PutMapping
    public ProfileResponse updateProfile(
            @RequestBody UpdateProfileRequest request) {

        return profileService.updateProfile(request);

    }

    @PutMapping("/change-password")
    public ApiResponse changePassword(
            @RequestBody ChangePasswordRequest request) {

        profileService.changePassword(request);

        return new ApiResponse(true, "Password changed successfully");

    }

}
