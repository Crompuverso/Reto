package com.kruger.vaccination.DTO;

import java.util.List;

import lombok.Data;

@Data
public class JwtDTO {
    private String name;
    private List<String> roles;
    private String surname;
    private String token;
    private String type;
    private String username;

    public JwtDTO(String name, List<String> roles, String surname, String token, String username) {
        this.name = name;
        this.roles = roles;
        this.surname = surname;
        this.token = token;
        this.type = "Bearer";
        this.username = username;
    }
}