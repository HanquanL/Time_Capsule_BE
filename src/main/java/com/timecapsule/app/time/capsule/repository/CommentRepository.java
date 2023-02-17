package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Comment;
import com.timecapsule.app.time.capsule.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByPost(Post post);
}
