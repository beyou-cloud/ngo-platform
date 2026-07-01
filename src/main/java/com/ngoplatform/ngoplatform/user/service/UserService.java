package com.ngoplatform.ngoplatform.user.service;

import com.ngoplatform.ngoplatform.common.exception.EmailAlreadyExistsException;
import com.ngoplatform.ngoplatform.common.exception.InvalidCredentialsException;
import com.ngoplatform.ngoplatform.user.dto.LoginRequest;
import com.ngoplatform.ngoplatform.user.dto.LoginResponse;
import com.ngoplatform.ngoplatform.user.dto.RegisterRequest;
import com.ngoplatform.ngoplatform.user.dto.RegisterResponse;
import com.ngoplatform.ngoplatform.user.entity.User;
import com.ngoplatform.ngoplatform.user.enums.UserRole;
import com.ngoplatform.ngoplatform.user.enums.UserStatus;
import com.ngoplatform.ngoplatform.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        // Store only the BCrypt hashed password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Assign default values for every new user
        user.setRole(UserRole.DONOR);
        user.setStatus(UserStatus.ACTIVE);
        user.setEmailVerified(false);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "Registration successful"
        );
    }

    @Transactional(readOnly = true)
    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                "Login successful"
        );
    }
}