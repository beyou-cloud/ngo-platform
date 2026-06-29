package com.ngoplatform.ngoplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {

    private Long id;
    private String fullName;
    private String email;
    private String message;
}