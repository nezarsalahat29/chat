package com.ultra.rmq.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message = "Please provide a username")
    private String username;
    @NotBlank(message = "Please provide a password")
    private String password;
}
