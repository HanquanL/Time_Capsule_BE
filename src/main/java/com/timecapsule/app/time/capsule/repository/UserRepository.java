package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
