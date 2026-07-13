package com.satish.expense_tracker.service.impl;

import com.satish.expense_tracker.dto.request.LoginRequest;
import com.satish.expense_tracker.dto.response.LoginResponse;
import com.satish.expense_tracker.entity.User;
import com.satish.expense_tracker.repository.UserRepository;
import com.satish.expense_tracker.security.JwtService;
import com.satish.expense_tracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow();

        String token = jwtService.generateToken(

                new org.springframework.security.core.userdetails.User(

                        user.getEmail(),

                        user.getPassword(),

                        java.util.List.of()

                )

        );

        return new LoginResponse(

                token,

                user.getName(),

                user.getEmail(),

                user.getRole().name()

        );

    }

}