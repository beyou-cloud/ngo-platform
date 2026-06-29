package com.ngoplatform.ngoplatform.user.controller;

import com.ngoplatform.ngoplatform.user.dto.RegisterRequest;
import com.ngoplatform.ngoplatform.user.dto.RegisterResponse;
import com.ngoplatform.ngoplatform.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(
            @Valid @RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }
}