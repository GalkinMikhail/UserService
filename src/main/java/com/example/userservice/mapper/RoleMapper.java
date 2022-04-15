package com.example.userservice.mapper;


import com.example.userservice.dto.RoleDto;
import com.example.userservice.model.Role;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Named("roleToRoleDto")
    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto roleDto);

    @IterableMapping(qualifiedByName = "roleToRoleDto")
    List<RoleDto> allToDto(List<Role> roles);
}
