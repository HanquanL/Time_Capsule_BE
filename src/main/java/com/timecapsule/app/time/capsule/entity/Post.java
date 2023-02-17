package com.timecapsule.app.time.capsule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Lob
    @NotBlank
    private String content;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_time_capsule_id", nullable = false)
    private SubTimeCapsule subTimeCapsule;

    private Integer voteCount = 0;

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = Instant.now();
    }

}
