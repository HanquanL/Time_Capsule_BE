package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.SubTimeCapsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTimeCapsuleRepository extends JpaRepository<SubTimeCapsule, Long> {
}
