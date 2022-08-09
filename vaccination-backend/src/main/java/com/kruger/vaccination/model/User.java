package com.kruger.vaccination.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "worker", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dni", name = "dni"),
        @UniqueConstraint(columnNames = "email", name = "email"),
        @UniqueConstraint(columnNames = "username", name = "username")
})
public class User {

    private String address;

    @DateTimeFormat
    private Date birthDate;

    @Column(length = 10)
    @NotBlank(message = "La cédula es requerida")
    private String dni;

    @Email
    @NotBlank(message = "El correo electrónico es requerido")
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo de nombre o nombres es requerido")
    private String name;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "roleId"), joinColumns = @JoinColumn(name = "workerId"), name = "user_roles")
    private Set<Role> roles = new HashSet<>();

    @NotBlank(message = "El campo de apellido o apellidos es requerido")
    private String surname;

    @NotBlank(message = "El nombre de usuario es requerido")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserVaccine> userVaccines = new HashSet<>();

    private boolean vaccination;

    public User() {
    }

    public User(String dni, String email, String name, String password, String surname, String username) {
        this.dni = dni;
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.username = username;
    }

    public User(String address, Date birthDate, String dni, String email, Long id, String name, String phone,
            String surname, boolean vaccination) {
        this.address = address;
        this.birthDate = birthDate;
        this.dni = dni;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.vaccination = vaccination;
    }
}