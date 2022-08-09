package com.kruger.vaccination.Service;

import org.springframework.security.core.Authentication;

import com.kruger.vaccination.DTO.JwtDTO;

public interface IAuthService {
    public JwtDTO login(Authentication authentication);
}