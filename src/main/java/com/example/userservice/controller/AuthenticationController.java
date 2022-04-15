package com.example.userservice.controller;

import com.example.userservice.dto.AuthenticationRequestDTO;
import com.example.userservice.exception.BadCredentialsException;
import com.example.userservice.model.User;
import com.example.userservice.security.JwtTokenProvider;
import com.example.userservice.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.getUsername();
            User user = userService.findByUsername(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,requestDTO.getPassword()));

            String token = jwtTokenProvider.createToken(username,user.getRoles());
            Map<Object,Object> response = new HashMap<>();
            response.put("username", requestDTO.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);

        }
        catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password","username/pass");
        }
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}
