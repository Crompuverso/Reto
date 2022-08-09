package com.kruger.vaccination.DTO;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserVaccineDTO {
    @NotBlank(message = "El número de dosis es requerido")
    private int dose;

    @DateTimeFormat
    @NotBlank(message = "La fecha de vacunación es requerida")
    private Date vaccineDate;

    private Long userId;

    private Long vaccineId;
}