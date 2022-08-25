package com.kruger.vaccination.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@IdClass(UserVaccinePK.class)
@Table
public class UserVaccine {

    @NotBlank(message = "El número de dosis es requerido")
    private int dose;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "workerId"), name = "workerId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "vaccineId"), name = "vaccineId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Vaccine vaccine;

    @DateTimeFormat
    @NotBlank(message = "La fecha de vacunación es requerida")
    private Date vaccineDate;

    public UserVaccine() {
    }

    public UserVaccine(int dose, User user, Vaccine vaccine, Date vaccineDate) {
        this.dose = dose;
        this.user = user;
        this.vaccine = vaccine;
        this.vaccineDate = vaccineDate;
    }
}