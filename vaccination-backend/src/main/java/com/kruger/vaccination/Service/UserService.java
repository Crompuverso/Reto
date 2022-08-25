package com.kruger.vaccination.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.vaccination.DTO.ExtendedUserDTO;
import com.kruger.vaccination.DTO.UserDTO;
import com.kruger.vaccination.Exception.NotFoundExceptionHandler;
import com.kruger.vaccination.model.ERole;
import com.kruger.vaccination.model.Role;
import com.kruger.vaccination.model.User;
import com.kruger.vaccination.model.UserVaccine;
import com.kruger.vaccination.model.Vaccine;
import com.kruger.vaccination.repository.RoleRepository;
import com.kruger.vaccination.repository.UserRepository;
import com.kruger.vaccination.repository.VaccineRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler("User", "id", id.toString()));
        userRepository.delete(user);
    }

    @Override
    public UserDTO find(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler("User", "id", id.toString()));
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(role -> {
            roles.add(role.getName().name());
        });
        return new UserDTO(user.getDni(), user.getEmail(), user.getId(), user.getName(), roles, user.getSurname(),
                user.getUsername());
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            Set<String> roles = new HashSet<>();
            user.getRoles().forEach(role -> {
                roles.add(role.getName().name());
            });
            users.add(new UserDTO(user.getDni(), user.getEmail(), user.getId(), user.getName(), roles,
                    user.getSurname(), user.getUsername()));
        });
        return users;
    }

    private String generatePassword(String dni, String email) {
        String password = "";
        String[] emailParts = email.split("@");
        password += emailParts[0].toUpperCase().charAt(0);
        password += emailParts[0].substring(1);
        password += dni;
        password += ".";
        return password;
    }

    private String generateUsername(String name, String surname) {
        int count = 0;
        String[] names = name.split(" ");
        String[] surnames = surname.split(" ");
        String username = "";
        for (String namePart : names) {
            username += namePart.toLowerCase().charAt(0);
        }
        username += surnames[0].toLowerCase();
        while (userRepository.existsByUsername(username)) {
            if (count == 0) {
                count++;
            } else {
                username = username.substring(0, username.length() - 1);
            }
            username += count;
        }
        return username;
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        Long id = userDTO.getId();
        User user;
        if (id != null && id > 0) {
            user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundExceptionHandler("User", "id", id.toString()));
            user.setDni(userDTO.getDni());
            user.setEmail(userDTO.getEmail());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
        } else {
            user = new User(userDTO.getDni(), userDTO.getEmail(), userDTO.getName(),
                    passwordEncoder.encode(generatePassword(userDTO.getDni(), userDTO.getEmail())),
                    userDTO.getSurname(), generateUsername(userDTO.getName(), userDTO.getSurname()));
        }
        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(strRole -> {
            switch (strRole) {
                case "administrador":
                    roles.add(roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new NotFoundExceptionHandler("Role", "nombre",
                                    ERole.ROLE_ADMIN.name())));
                    break;
                default:
                    roles.add(roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(
                                    () -> new NotFoundExceptionHandler("Role", "nombre", ERole.ROLE_USER.name())));
                    break;
            }
        });
        user.setRoles(roles);
        if (userDTO.getId() != null && userDTO.getId() > 0) {
            user.setId(userDTO.getId());
        }
        userRepository.save(user);
    }

    @Override
    public void update(Long id, ExtendedUserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler("User", "id", id.toString()));
        user.setAddress(userDTO.getAddress());
        user.setBirthDate(userDTO.getBirthDate());
        user.setDni(userDTO.getDni());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setSurname(userDTO.getSurname());
        user.setVaccination(userDTO.isVaccination());
        Set<UserVaccine> userVaccines = new HashSet<>();
        userDTO.getUserVaccines().forEach(userVaccine -> {
            Vaccine vaccine = vaccineRepository.findById(userVaccine.getVaccineId()).orElseThrow(
                    () -> new NotFoundExceptionHandler("Vacuna", "id", userVaccine.getVaccineId().toString()));
            userVaccines.add(new UserVaccine(userVaccine.getDose(), user, vaccine, userVaccine.getVaccineDate()));
        });
        user.setUserVaccines(userVaccines);
        userRepository.save(user);
    }
}