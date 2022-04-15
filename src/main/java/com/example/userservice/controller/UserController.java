package com.example.userservice.controller;

import com.example.userservice.controller.Urls.Urls;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(Urls.User.FULL)
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(Urls.User.id.FULL)
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping(Urls.User.id.FULL)
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(Urls.User.FULL)
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(Urls.User.id.FULL)
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserDto userDto){
        this.userService.updateUser(id,userDto);
        return ResponseEntity.ok("User updated successfully");
    }
}
