package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.SubTimeCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubTimeCapsuleRepository extends JpaRepository<SubTimeCapsule, Long> {
    Optional<SubTimeCapsule> findByName(String name);
}
