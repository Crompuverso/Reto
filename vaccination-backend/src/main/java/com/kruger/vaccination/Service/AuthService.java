package com.kruger.vaccination.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.vaccination.DTO.JwtDTO;
import com.kruger.vaccination.security.jwt.JwtUtil;
import com.kruger.vaccination.security.services.UserDetailsImpl;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    @Transactional
    public JwtDTO login(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtDTO(userDetailsImpl.getName(), roles, userDetailsImpl.getSurname(), token,
                userDetailsImpl.getUsername());
    }
}