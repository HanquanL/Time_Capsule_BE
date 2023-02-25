package com.timecapsule.app.time.capsule.service;


import com.timecapsule.app.time.capsule.exception.SpringTimeCapsuleException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    private KeyStore keyStore;
    private static final Logger logger = Logger.getLogger(JwtProvider.class.getName());

    @PostConstruct
    public void init(){
        try{
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/timecapsule.jks");
            keyStore.load(resourceAsStream, "password".toCharArray());
        }catch ( KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e){
            throw new SpringTimeCapsuleException("Exception occurred while loading keystore");
        }
    }

    public String generateToken(Authentication authentication){
        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();
        logger.info("Generating token for user: " + username );
        try {
            String token = Jwts.builder()
                    .setSubject(principal.getUsername())
                    .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                    .compact();
            logger.info("Token generated successfully for user: " + username);
            return token;
        }catch (Exception e){
            logger.severe("Exception occurred while generating token for user: " + username + ", " + e.getMessage());
            throw new SpringTimeCapsuleException("Exception occurred while generating token for user: " + username + ", reason: " + e.getMessage());
        }
    }

    private PrivateKey getPrivateKey(){
        try{
            PrivateKey privateKey = (PrivateKey) keyStore.getKey("mykey", "password".toCharArray());
            if(privateKey == null){
                throw new SpringTimeCapsuleException("Private key not founding keystore");
            }
            return privateKey;
        }catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e){
            throw new SpringTimeCapsuleException("Exception occured while retrieving public key from keystore");
        }
    }
}
