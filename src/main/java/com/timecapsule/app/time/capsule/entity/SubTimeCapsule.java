package com.timecapsule.app.time.capsule.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_time_capsules")
@Getter
@Setter
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
