package com.timecapsule.app.time.capsule.entity;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table
@Data
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

    private Instant created;
    private boolean enabled;

}
