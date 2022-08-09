package com.kruger.vaccination.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserVaccinePK implements Serializable {
    private int user;
    private int vaccine;
}
