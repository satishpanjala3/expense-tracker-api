package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.ChangePasswordRequest;
import com.satish.expense_tracker.dto.request.UpdateProfileRequest;
import com.satish.expense_tracker.dto.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getProfile();

    ProfileResponse updateProfile(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);

}