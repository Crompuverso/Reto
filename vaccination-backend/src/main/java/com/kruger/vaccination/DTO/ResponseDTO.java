package com.kruger.vaccination.DTO;

import lombok.Data;

@Data
public class ResponseDTO {
    String message;

    public ResponseDTO() {
    }

    public ResponseDTO(String message) {
        this.message = message;
    }
}