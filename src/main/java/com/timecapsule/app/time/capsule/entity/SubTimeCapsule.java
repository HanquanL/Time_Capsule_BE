package com.timecapsule.app.time.capsule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sub_time_capsules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTimeCapsule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "subTimeCapsule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "subTimeCapsule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    public SubTimeCapsule(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
