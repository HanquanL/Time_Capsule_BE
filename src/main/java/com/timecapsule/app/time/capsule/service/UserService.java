package com.timecapsule.app.time.capsule.service;

import com.timecapsule.app.time.capsule.dto.SignupRequest;
import com.timecapsule.app.time.capsule.entity.User;
import com.timecapsule.app.time.capsule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User registerUser(SignupRequest signupRequest);
    User updateUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);

}
