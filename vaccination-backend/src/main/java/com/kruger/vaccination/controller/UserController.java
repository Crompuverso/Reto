package com.kruger.vaccination.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.vaccination.DTO.ExtendedUserDTO;
import com.kruger.vaccination.DTO.MessageDTO;
import com.kruger.vaccination.DTO.UserDTO;
import com.kruger.vaccination.Service.IUserService;
import com.kruger.vaccination.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new MessageDTO("Usuario eliminado exitosamente"));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> register(@RequestBody @Valid UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageDTO("Error:El email ya está actualmente en uso"));
        }
        userService.save(userDTO);
        return ResponseEntity.ok(new MessageDTO("Usuario registrado exitosamente"));
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageDTO> update(@PathVariable Long id, @RequestBody @Valid ExtendedUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageDTO("Error:El email ya está actualmente en uso"));
        }
        userService.update(id, userDTO);
        return ResponseEntity.ok(new MessageDTO("Usuario actualizado exitosamente"));
    }
}