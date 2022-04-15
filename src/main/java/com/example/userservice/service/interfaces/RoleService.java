package com.example.userservice.service.interfaces;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleDto roleDto);
    void deleteRole(Long id);
    RoleDto getById(Long id);
    List<RoleDto> getAll();
    void update(Long id, RoleDto roleDto);
}
