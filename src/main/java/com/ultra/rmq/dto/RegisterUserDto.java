package com.ultra.rmq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank(message = "Please provide a username")
    private String username;
    @NotBlank(message = "Please provide an email")
    private String email;
    @NotBlank(message = "Please provide a password")
    @Size(min = 4, max = 15)
    private String password;
    @NotBlank(message = "Please provide a full name")
    private String fullName;
    @JsonProperty(value = "isUserAdmin")
    private boolean isUserAdmin;
}
