package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Comment;
import com.timecapsule.app.time.capsule.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
