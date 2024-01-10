package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
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
    private int reportCount;
    private String reportLastVisit;

    @OneToMany
    @JoinColumn(name = "rapNum")
    private List<Reports> reports;

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


    public void updateReportInformation() {
        if (reports != null && !reports.isEmpty()) {
            this.reportCount = reports.size();

            // Find the last visit date
            Reports lastReport = reports.get(reports.size() - 1);
            this.reportLastVisit = lastReport.getRapDate();
        } else {
            this.reportCount = 0;
            this.reportLastVisit = null;
        }
    }

    public void setPatientPassword(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.patientPassword = passwordEncoder.encode(plainPassword);
    }
}
