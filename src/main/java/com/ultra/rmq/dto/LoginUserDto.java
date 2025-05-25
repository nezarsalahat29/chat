package com.ultra.rmq.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message = "Please provide a email")
    private String email;
    @NotBlank(message = "Please provide a password")
    private String password;
}
