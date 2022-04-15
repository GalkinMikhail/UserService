package com.example.userservice.service.implement;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.mapper.RoleMapper;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.Role;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleMapper roleMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRole() {
        RoleDto roleDto = new RoleDto(1L,"ROLE_MODERATOR");
        Role savedRole = roleService.createRole(roleDto);

        assertNotNull(savedRole);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void deleteRole() {
        Role role = new Role(1L,"ROLE_MODERATOR");

        roleService.deleteRole(1L);

        verify(roleRepository).deleteById(1L);
    }

    @Test
    void getAll() {
        Role role = new Role(1L,"ADMIN");
        Role role1 = new Role(2L,"USER");
        Role role2 = new Role(3L,"MODERATOR");

        List<Role> roleList = new ArrayList<>(List.of(role,role2,role1));

        when(roleRepository.findAll()).thenReturn(roleList);
        assertEquals(roleMapper.allToDto(roleList),roleService.getAll());
    }

    @Test
    void update() {
        RoleDto roleDto = new RoleDto(null,"ROLE_MODERATOR");
        Long id = 1L;

        roleService.update(id,roleDto);

        verify(roleRepository,times(1)).updateRoleById(id, roleDto.getName());
    }
}