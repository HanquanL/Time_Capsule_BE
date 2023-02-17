package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Post;
import com.timecapsule.app.time.capsule.entity.SubTimeCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findBySubTimeCapsuleOrderByVoteCountDesc(SubTimeCapsule subTimeCapsule);
}
