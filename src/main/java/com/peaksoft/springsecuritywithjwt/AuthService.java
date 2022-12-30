package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.dto.AuthRequest;
import com.peaksoft.springsecuritywithjwt.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final TokenUtilities tokenUtilities;
    private  final AuthenticationManager authenticationManager;
    public AuthResponse authenticate(AuthRequest authRequest) {
        //  authentication manager
       Authentication authentication =authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(authRequest.email(),authRequest.password())
       );
       // generate token

      return  AuthResponse.builder()
              .token(tokenUtilities.generateToken(authentication.getName()))
              .build();
    }
}
