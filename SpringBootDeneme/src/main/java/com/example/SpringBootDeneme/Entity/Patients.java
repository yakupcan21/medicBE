package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Data
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int reportCount;
    private String reportLastVisit;


    public Patients() {
        super();
    }

    public Patients(String patientName, String patientSurname, String patientDateOfBirth, String patientPhoneNo,
                    String patientEmail, String patientPassword, int patientAge, int patientHeight, int patientWeight, int patientBmi, int reportCount, String reportLastVisit) {
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
        this.reportCount = reportCount;
        this.reportLastVisit = reportLastVisit;
    }

    public void updateReportInformation(List<Reports> reports) {
        // Rapor sayısını güncelle
        this.reportCount = reports.size();

        // En son ziyaret tarihini bul
        if (!reports.isEmpty()) {
            Reports latestReport = reports.stream()
                    .max(Comparator.comparing(Reports::getRapDate))
                    .orElseThrow(NoSuchElementException::new);

            this.reportLastVisit = latestReport.getRapDate();
        }
    }


    public void setPatientPassword(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.patientPassword = passwordEncoder.encode(plainPassword);
    }
}
