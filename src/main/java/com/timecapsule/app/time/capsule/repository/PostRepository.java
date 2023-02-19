package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Post;
import com.timecapsule.app.time.capsule.entity.SubTimeCapsule;
import com.timecapsule.app.time.capsule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBysubTimeCapsule(SubTimeCapsule subTimeCapsule);

    List<Post> findByUser(User user);
}
