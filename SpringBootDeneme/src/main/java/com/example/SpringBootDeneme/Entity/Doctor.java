package com.example.SpringBootDeneme.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long docId;
    private String docName;
    private String docSurname;
    private String docDateOfBirth;
    private String docPhoneNo;
    private String docEmail;
    private String docPassword;
    private int docAge;
    private int docHeight;
    private float docWeight;
    private float docBmi;
    private String docTitle;
    private String docDepartment;
    private String docHospital;

    public Doctor() { super(); }

    public Doctor(long docId, String docName, String docSurname, String docDateOfBirth, String docPhoneNo, String docEmail, String docPassword, int docAge, int docHeight, float docWeight, float docBmi, String docTitle, String docDepartment, String docHospital) {
        this.docId = docId;
        this.docName = docName;
        this.docSurname = docSurname;
        this.docDateOfBirth = docDateOfBirth;
        this.docPhoneNo = docPhoneNo;
        this.docEmail = docEmail;
        setDocPassword(docPassword);
        this.docAge = docAge;
        this.docHeight = docHeight;
        this.docWeight = docWeight;
        this.docBmi = docBmi;
        this.docTitle = docTitle;
        this.docDepartment = docDepartment;
        this.docHospital = docHospital;
    }
    public void setDocPassword(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.docPassword = passwordEncoder.encode(plainPassword);
    }

}
