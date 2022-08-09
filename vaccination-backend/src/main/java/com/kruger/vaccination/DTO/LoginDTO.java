package com.kruger.vaccination.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String password;

    @NotBlank
    private String username;
}