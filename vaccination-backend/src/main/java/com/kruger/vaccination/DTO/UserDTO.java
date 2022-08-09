package com.kruger.vaccination.DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotBlank(message = "No se ingresó una cédula")
    private String dni;

    @Email
    @NotBlank(message = "No se ingresó un email")
    private String email;

    @NotBlank(message = "No se ingresó el nombre o nombres")
    private String name;

    private Set<String> roles;

    @NotBlank(message = "No se ingresó el apellido o apellidos")
    private String surname;

    public UserDTO(String dni, String email, String name, Set<String> roles, String surname) {
        this.dni = dni;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.surname = surname;
    }
}