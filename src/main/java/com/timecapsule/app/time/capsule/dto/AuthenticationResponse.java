package com.timecapsule.app.time.capsule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String usernae;
}
