package com.timecapsule.app.time.capsule.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6)
    private String password;
    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_time_capsule_id", referencedColumnName = "id")
    private SubTimeCapsule subTimeCapsule;

    private Instant created;
    private boolean enabled;

    public User(String username, String email, String encode) {
    }
}
