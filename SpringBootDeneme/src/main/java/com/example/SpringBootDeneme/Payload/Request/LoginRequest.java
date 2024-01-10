package com.example.SpringBootDeneme.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private String password;

}
