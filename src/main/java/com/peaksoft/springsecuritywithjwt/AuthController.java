package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.dto.AuthRequest;
import com.peaksoft.springsecuritywithjwt.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private  final  AuthService authService;

    // login   username ,password

    @PostMapping
    @PreAuthorize("permitAll()")
    AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
    return  authService.authenticate(authRequest);
    }
}
