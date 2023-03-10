package com.timecapsule.app.time.capsule.service;

import com.timecapsule.app.time.capsule.dto.AuthenticationResponse;
import com.timecapsule.app.time.capsule.dto.LoginRequest;
import com.timecapsule.app.time.capsule.dto.SignupRequest;
import com.timecapsule.app.time.capsule.entity.NotificationEmail;
import com.timecapsule.app.time.capsule.entity.User;
import com.timecapsule.app.time.capsule.entity.VerificationToken;
import com.timecapsule.app.time.capsule.exception.SpringTimeCapsuleException;
import com.timecapsule.app.time.capsule.repository.UserRepository;
import com.timecapsule.app.time.capsule.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static java.time.Instant.now;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailBuilder mailBuilder;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encodePassword(signupRequest.getPassword()));
        user.setCreated(now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        String message = mailBuilder.build("http://localhost:8080/api/auth/accountVerification" + "/" + token);
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token){
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringTimeCapsuleException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringTimeCapsuleException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new
                        UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authenticationToken = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
