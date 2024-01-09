package com.example.SpringBootDeneme.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private String password;

}
