package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CreateReport {

    @Id
    private String imgName;

    private String findings;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "rapNum") // Assuming rapNum is the foreign key in CreateReport referencing Reports
    private Reports reports;

    @OneToOne
    @JoinColumn(name = "patientId") // Assuming patientId is the foreign key in CreateReport referencing Patients
    private Patients patients;

    public CreateReport() {
        super();
    }

    // Assuming you need a constructor that takes the related entities as parameters
    public CreateReport(String imgName, String findings, byte[] data, Reports reports, Patients patients) {
        this.imgName = imgName;
        this.findings = findings;
        this.data = data;
        this.reports = reports;
        this.patients = patients;
    }
}
