package com.kruger.vaccination.Exception;

import org.springframework.security.authentication.BadCredentialsException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BadCredentialsExceptionHandler extends BadCredentialsException {

    private String username;

    public BadCredentialsExceptionHandler(String username) {
        super(String.format("Par nombre de usuario y contraseña no encontrados para: %s", username));
        this.username = username;
    }
}