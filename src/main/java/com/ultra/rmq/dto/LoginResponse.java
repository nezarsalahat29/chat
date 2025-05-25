package com.ultra.rmq.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponse {
    private String token;
    private long expiresIn;

}
