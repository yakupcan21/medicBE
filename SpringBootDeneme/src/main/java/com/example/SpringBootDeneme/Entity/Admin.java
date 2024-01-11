package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Data
public class Admin {

    @Id
    private long id;
    private String adminName;

    private String adminPassword;


    public Admin() {
        super();
    }

    public Admin(String adminName, String adminPassword) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }



}