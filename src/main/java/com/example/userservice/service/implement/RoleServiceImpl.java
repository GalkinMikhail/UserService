package com.example.userservice.service.implement;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.mapper.RoleMapper;
import com.example.userservice.model.Role;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;


    @Override
    public Role createRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
        return role;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto getById(Long id) {
        return roleMapper.roleToRoleDto(roleRepository.getById(id));
    }

    @Override
    public List<RoleDto> getAll() {
        return roleMapper.allToDto(roleRepository.findAll());
    }

    @Transactional
    @Override
    public void update(Long id, RoleDto roleDto) {
        roleRepository.updateRoleById(id,roleDto.getName());
    }
}
