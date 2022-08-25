package com.kruger.vaccination.Service;

import java.util.List;

import com.kruger.vaccination.DTO.ExtendedUserDTO;
import com.kruger.vaccination.DTO.UserDTO;

public interface IUserService {
    public void delete(Long id);

    public UserDTO find(Long id);

    public List<UserDTO> findAll();

    public void save(UserDTO userDTO);

    public void update(Long id, ExtendedUserDTO userDTO);
}