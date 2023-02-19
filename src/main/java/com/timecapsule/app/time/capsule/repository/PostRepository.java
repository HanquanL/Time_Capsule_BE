package com.timecapsule.app.time.capsule.repository;

import com.timecapsule.app.time.capsule.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
