package com.example.SpringBootDeneme.Controller;

import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Entity.Reports;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import com.example.SpringBootDeneme.Repository.PatientsRepository;
import com.example.SpringBootDeneme.Repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsRepository reportsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping(path = "/seeAllReports")
    public Iterable<Reports> getAllReports() {
        return reportsRepository.findAll();
    }

    @GetMapping("/report/{rapNum}")
    public Optional<Reports> getReport(@PathVariable Long rapNum) {
        return reportsRepository.findById(rapNum);
    }

    @PostMapping(path = "/createReport/{patientId}/{doctorId}")
    public Reports post(@PathVariable Long patientId, @PathVariable Long doctorId, @RequestBody Reports reports) {
        Patients patient = patientsRepository.findById(patientId).orElse(null);
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        if (patient != null && doctor != null) {
            // Set patient and doctor for the report
            reports.setPatient(patient);
            reports.setDoctor(doctor);

            // Set other report information
            reports.generateRandomRapNum();
            reports.setCurrentDate();

            // Save report to ReportsRepository
            Reports savedReport = reportsRepository.save(reports);

            // Add the report to the patient's report list
            patient.getReports().add(savedReport);
            patientsRepository.save(patient);

            return savedReport;
        } else {
            // If patient or doctor not found, return null
            return null;
        }
    }
}
