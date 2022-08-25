package com.kruger.vaccination.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.vaccination.DTO.JwtDTO;
import com.kruger.vaccination.DTO.LoginDTO;
import com.kruger.vaccination.Exception.BadCredentialsExceptionHandler;
import com.kruger.vaccination.Service.IAuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            return ResponseEntity.ok(authService.login(authentication));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsExceptionHandler(loginDTO.getUsername());
        }
    }
}