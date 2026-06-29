package com.ngoplatform.ngoplatform.user.repository;

import com.ngoplatform.ngoplatform.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}