package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Data
public class Reports {

    @Id
    private String rapDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rapNum;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "docId") // Assuming docId is the foreign key in Reports referencing Doctor
    private Doctor doctor;

    @OneToOne(mappedBy = "reports", cascade = CascadeType.ALL)
    private CreateReport createReport;

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Reports() {
        super();
    }

    // Assuming you need a constructor that takes the related entities as parameters
    public Reports(String rapDate, Patients patient, Doctor doctor) {
        this.rapDate = rapDate;
        this.patient = patient;
        this.doctor = doctor;
        generateRandomRapNum();
        setCurrentDate();
    }


    public void generateRandomRapNum() {
        Random random = new Random();
        this.rapNum = (long) (random.nextDouble() * 10000000000L);
    }

    public void setCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.rapDate = currentDate.format(formatter);
    }

    public String getDoctorName() {
        return doctor != null ? doctor.getDocName() : null;
    }
}