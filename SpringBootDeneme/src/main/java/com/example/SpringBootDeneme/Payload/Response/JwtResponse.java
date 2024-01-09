package com.example.SpringBootDeneme.Payload.Response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String firstName, String surname, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }


    // Getters and setters
}
