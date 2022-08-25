package com.kruger.vaccination.DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "No se ingresó una cédula")
    private String dni;

    @Email
    @NotBlank(message = "No se ingresó un email")
    private String email;

    private Long id;

    @NotBlank(message = "No se ingresó el nombre o nombres")
    @Size(message = "El nombre debe tener al menos 3 caracteres", min = 3)
    private String name;

    private Set<String> roles;

    @NotBlank(message = "No se ingresó el apellido o apellidos")
    @Size(message = "El apellido debe tener al menos 3 caracteres", min = 3)
    private String surname;

    private String username;

    public UserDTO() {
    }

    public UserDTO(String dni, String email, Long id, String name, Set<String> roles, String surname, String username) {
        this.dni = dni;
        this.email = email;
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.surname = surname;
        this.username = username;
    }
}