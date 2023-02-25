package com.timecapsule.app.time.capsule.service;


import com.timecapsule.app.time.capsule.exception.SpringTimeCapsuleException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    private KeyStore keyStore;

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
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey("mykey", "password".toCharArray());
        }catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e){
            throw new SpringTimeCapsuleException("Exception occured while retrieving public key from keystore");
        }
    }
}
