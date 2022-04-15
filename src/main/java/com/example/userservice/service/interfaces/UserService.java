package com.example.userservice.service.interfaces;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    void deleteUser(Long id);
    UserDto getUserById(Long id);
    List<UserDto> getAll();
    void updateUser(Long id, UserDto userDto);
    User findByUsername(String username);
}
