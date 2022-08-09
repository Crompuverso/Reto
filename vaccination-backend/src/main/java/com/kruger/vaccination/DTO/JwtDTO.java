package com.kruger.vaccination.DTO;

import java.util.List;

import lombok.Data;

@Data
public class JwtDTO {
    private String dni;
    private String email;
    private List<String> roles;
    private String token;
    private String type;
    private String username;

    public JwtDTO(String dni, String email, List<String> roles, String token, String username) {
        this.dni = dni;
        this.email = email;
        this.roles = roles;
        this.token = token;
        this.type = "Bearer";
        this.username = username;
    }
}