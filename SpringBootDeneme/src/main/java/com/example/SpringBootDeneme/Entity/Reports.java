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
    private long rapNum;
    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patients patient;

/*
    @OneToOne(mappedBy = "CreateReport", cascade = CascadeType.ALL)
    private CreateReport CreateReport;*/

    public Reports() {
        super();
    }

    public Reports(String rapDate, long rapNum, String doctorName) {
        this.rapDate = rapDate;
        this.rapNum = rapNum;
        this.doctorName = doctorName;
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
