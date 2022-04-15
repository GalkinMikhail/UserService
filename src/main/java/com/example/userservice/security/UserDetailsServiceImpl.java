package com.example.userservice.security;

import com.example.userservice.model.User;
import com.example.userservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        SecurityUser securityUser = JwtUserFactory.create(user);
        return securityUser;
    }

}
