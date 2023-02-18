package com.timecapsule.app.time.capsule.service;

import com.timecapsule.app.time.capsule.dto.SignupRequest;
import com.timecapsule.app.time.capsule.entity.User;
import com.timecapsule.app.time.capsule.exception.BadRequestException;
import com.timecapsule.app.time.capsule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(SignupRequest signupRequest){
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BadRequestException("Email is already in use!");
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        return userRepository.save(user);
    }

}
