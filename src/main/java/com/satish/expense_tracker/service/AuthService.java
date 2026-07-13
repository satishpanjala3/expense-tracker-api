package com.satish.expense_tracker.service;

import com.satish.expense_tracker.dto.request.LoginRequest;
import com.satish.expense_tracker.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}