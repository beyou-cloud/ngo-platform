package com.ngoplatform.ngoplatform.user.dto;

import com.ngoplatform.ngoplatform.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String fullName;
    private String email;
    private UserRole role;
    private String message;
}