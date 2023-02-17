package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Post;
import com.timecapsule.app.time.capsule.entity.User;
import com.timecapsule.app.time.capsule.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByPostAndUser(Post post, User user);
}
