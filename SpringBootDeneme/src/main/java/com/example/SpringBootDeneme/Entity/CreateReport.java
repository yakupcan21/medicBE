package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class CreateReport {

    @Id
    private String imgName;
    @Lob
    private byte[] imageData;
    @OneToOne
    private Reports reports;
    @OneToOne
    private Patients patients;

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public CreateReport() {
        super();
    }

    public CreateReport(String imgName, byte[] imageData, Reports reports, Patients patients) {
        this.imgName = imgName;
        this.imageData = imageData;
        this.reports = reports;
        this.patients = patients;
    }
}
