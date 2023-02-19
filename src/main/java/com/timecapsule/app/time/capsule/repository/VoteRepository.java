package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Post;
import com.timecapsule.app.time.capsule.entity.User;
import com.timecapsule.app.time.capsule.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
}
