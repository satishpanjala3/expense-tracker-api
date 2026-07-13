package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.AddUserRequest;
import com.satish.expense_tracker.dto.request.UpdateUserRequest;
import com.satish.expense_tracker.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    UserResponse addUser(AddUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

}