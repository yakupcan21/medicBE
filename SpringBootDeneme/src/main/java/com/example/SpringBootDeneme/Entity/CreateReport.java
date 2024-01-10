package com.example.SpringBootDeneme.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CreateReport {

    @Id
    private String imgName;

    @Lob
    private byte[] data;

    @OneToOne
    private Reports reports;

    @OneToOne
    private Patients patients;


}
