package com.example.userservice.repository;

import com.example.userservice.dto.RoleDto;
import com.example.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Modifying
    @Query("update Role a set a.name = ?2 where a.Id = ?1")
    void updateRoleById(Long id,String name);
    Role findByName(String name);
}
