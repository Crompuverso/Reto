package com.kruger.vaccination.DTO;

import lombok.Data;

@Data
public class ErrorHandlerDTO {
    private String details;
    private String error;
    private String message;

    public ErrorHandlerDTO() {
    }

    public ErrorHandlerDTO(String details, String error, String message) {
        this.details = details;
        this.error = error;
        this.message = message;
    }
}