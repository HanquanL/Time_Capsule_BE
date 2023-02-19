package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Comment;
import com.timecapsule.app.time.capsule.entity.Post;
import com.timecapsule.app.time.capsule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
