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

    private String doctorName;


    @OneToOne(mappedBy = "reports", cascade = CascadeType.ALL)
    private CreateReport createReport;

    public void setPatient(Patients patient) {
        this.patient = patient;
    }


    public Reports() {
        super();
    }

    // Assuming you need a constructor that takes the related entities as parameters
    public Reports(String rapDate, Patients patient, Doctor doctor) {
        this.rapDate = rapDate;
        this.patient = patient;
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

}