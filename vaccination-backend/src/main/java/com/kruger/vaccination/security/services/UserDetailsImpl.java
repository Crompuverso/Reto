package com.kruger.vaccination.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kruger.vaccination.model.User;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;

    private String name;

    @JsonIgnore
    private String password;

    private String surname;

    private String username;

    public UserDetailsImpl(List<GrantedAuthority> authorities, String name, String password, String surname,
            String username) {
        this.authorities = authorities;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.username = username;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserDetailsImpl(authorities, user.getName(), user.getPassword(), user.getSurname(),
                user.getUsername());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}