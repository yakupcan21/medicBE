package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
public class Patients {

    @Id
    private long patientId;
    private String patientName;
    private String patientSurname;
    private String patientDateOfBirth; // Use Date or LocalDate for dateOfBirth
    private String patientPhoneNo; // Change data type to String
    private String patientEmail;
    private String patientPassword;
    private int patientAge;
    private int patientHeight;
    private int patientWeight;
    private float patientBmi;

    @OneToOne(mappedBy = "Patient", cascade = CascadeType.ALL)
    private Reports report;

    public Patients() {
        super();
    }

    public Patients(String patientName, String patientSurname, String patientDateOfBirth, String patientPhoneNo,
                    String patientEmail, String patientPassword, int patientAge, int patientHeight, int patientWeight, int patientBmi) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientPhoneNo = patientPhoneNo;
        setPatientPassword(patientPassword);
        this.patientEmail = patientEmail;
        this.patientAge = patientAge;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.patientBmi = patientBmi;
    }

    public void setPatientPassword(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.patientPassword = passwordEncoder.encode(plainPassword);
    }
}
