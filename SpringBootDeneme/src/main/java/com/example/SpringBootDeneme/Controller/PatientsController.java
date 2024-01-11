package com.example.SpringBootDeneme.Controller;


import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Entity.Reports;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import com.example.SpringBootDeneme.Repository.PatientsRepository;
import com.example.SpringBootDeneme.Repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    private PatientsRepository PatientsRepository;
    @Autowired
    private ReportsRepository ReportsRepository;

    @GetMapping(path = "/seeAllPatients")
    public Iterable<Patients> getAllPatients() {return PatientsRepository.findAll(); }

    @GetMapping("/patient/{patientId}")
    public List<Patients> getPatients(@PathVariable Integer patientId) {
        return PatientsRepository.findByPatientId(patientId);
    }

    @PutMapping("/updatePatientReports")
    public Patients updatePatientReports(@PathVariable Long patientId) {
        Patients patient = PatientsRepository.findById(patientId).orElse(null);

        if (patient != null) {
            List<Reports> patientReports = ReportsRepository.findByPatient_PatientId(patientId);
            patient.updateReportInformation(patientReports);

            return PatientsRepository.save(patient);
        } else {
            return null;
        }
    }


    @PostMapping(path = "/createPatient/")
    public ResponseEntity<?> post(@RequestBody Patients patients) {
        try {
            Patients savedPatient = PatientsRepository.save(patients);
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving patient: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public String login(@RequestBody Patients patients) {
        Long patientId = patients.getPatientId();
        String password = patients.getPatientPassword();

        Optional<Patients> patient = PatientsRepository.findById(patientId);

        if (patient.isPresent() && patient.get().getPatientPassword().equals(password)) {
            return String.valueOf(patients.getPatientId());
        } else {
            return "Invalid credentials!";
        }
    }

}