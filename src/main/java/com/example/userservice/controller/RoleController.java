package com.example.userservice.controller;

import com.example.userservice.controller.Urls.Urls;
import com.example.userservice.dto.RoleDto;
import com.example.userservice.model.Role;
import com.example.userservice.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(Urls.Role.FULL)
    public ResponseEntity<Role> create(@RequestBody RoleDto roleDto){
        return ResponseEntity.ok(this.roleService.createRole(roleDto));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(Urls.Role.id.FULL)
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
    @GetMapping(Urls.Role.id.FULL)
    public ResponseEntity<RoleDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.roleService.getById(id));
    }
    @GetMapping(Urls.Role.FULL)
    public ResponseEntity<List<RoleDto>> getAll(){
        return ResponseEntity.ok(this.roleService.getAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(Urls.Role.id.FULL)
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RoleDto roleDto){
        this.roleService.update(id,roleDto);
        return ResponseEntity.ok("Role updated successfully");
    }
 }
