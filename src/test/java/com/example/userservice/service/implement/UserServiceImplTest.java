package com.example.userservice.service.implement;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserTest() {
        UserDto userDto = new UserDto(null,"username","pass");
        User savedUser = userService.createUser(userDto);

        assertNotNull(savedUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUser() {
        UserDto userDto = new UserDto(null,"username","pass");
        Long id = 1L;

        userService.updateUser(id,userDto);

        verify(userRepository,times(1)).updateById(userDto.getUsername(),
                userDto.getPassword(),
                id);
    }

    @Test
    void getAllTest() {
        Set<Role> userRoles = new HashSet<>();
        User user = new User(1L,"user1","$2a$12$7d8nkkH0f7Hwz6Dz7c01tubvFvSsHtrU2V24EYa3FH3xndXT3bIpG",userRoles);
        User user2 = new User(2L,"user2","$2a$12$7d8nkkH0f7Hwz6Dz7c01tubvFvSsHtrU2V24EYa3FH3xndXT3bIpG",userRoles);
        User user3 = new User(3L,"user3","$2a$12$7d8nkkH0f7Hwz6Dz7c01tubvFvSsHtrU2V24EYa3FH3xndXT3bIpG",userRoles);
        List<User> userList = new ArrayList<>(Arrays.asList(user,user2,user3));

        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userMapper.allToDto(userList),userService.getAll());
    }

    @Test
    void deleteUserTest(){
        Set<Role> userRoles = new HashSet<>();
        User user = new User(1L,"user1","$2a$12$7d8nkkH0f7Hwz6Dz7c01tubvFvSsHtrU2V24EYa3FH3xndXT3bIpG",userRoles);
        userService.deleteUser(1L);

        verify(userRepository,times(1)).deleteById(1L);

    }
}