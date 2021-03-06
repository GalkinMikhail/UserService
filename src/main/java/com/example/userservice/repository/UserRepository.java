package com.example.userservice.repository;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Query("update User u set u.username = ?1, u.password = ?2 where u.id = ?3")
    void updateById(String username, String password, Long id);

    User findByUsername(String name);
}
