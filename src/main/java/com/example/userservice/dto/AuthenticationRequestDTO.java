package com.example.userservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
