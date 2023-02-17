package com.timecapsule.app.time.capsule.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean upvote;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Vote(boolean upvote, User user, Post post) {
        this.upvote = upvote;
        this.user = user;
        this.post = post;
    }
}
