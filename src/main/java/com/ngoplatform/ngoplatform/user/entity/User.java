package com.ngoplatform.ngoplatform.user.entity;
import com.ngoplatform.ngoplatform.user.enums.UserRole;
import com.ngoplatform.ngoplatform.user.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.ngoplatform.ngoplatform.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private Boolean emailVerified = false;

}