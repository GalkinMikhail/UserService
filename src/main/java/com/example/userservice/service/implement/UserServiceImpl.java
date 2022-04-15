package com.example.userservice.service.implement;

import com.example.userservice.dto.UserDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(UserDto userDto) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        User user = new User();
        user.setUsername(userDto.getUsername());
        String password = new BCryptPasswordEncoder().encode(userDto.getPassword());
        user.setPassword(password);
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " not found","not found")));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.allToDto(userRepository.findAll());
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserDto userDto) {
        String password = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userRepository.updateById(userDto.getUsername(), password, id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
