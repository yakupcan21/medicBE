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
    private ReportsRepository ReportsRepository;
    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping(path ="/seeAllReports")
    public Iterable<Reports> getAllDoctors(){ return ReportsRepository.findAll(); }

    @GetMapping("/report/{rapNum}")
    public List<Reports> getReport(@PathVariable Long rapNum) {
        return ReportsRepository.findByRapNum(rapNum);
    }

    @PostMapping(path = "/createReport/{patientId}")
    public Reports post(@PathVariable Long patientId, @RequestBody Reports reports) {
        Patients patient = patientsRepository.findById(patientId).orElse(null);

        if (patient != null) {
            // Hastayı rapora ekle
            reports.setPatient(patient);

            // Diğer rapor bilgilerini ayarla
            reports.generateRandomRapNum();
            reports.setCurrentDate();

            // ReportsRepository'ye kaydet
            Reports savedReport = ReportsRepository.save(reports);

            // Hastanın rapor listesine ekleyin
            patient.getReports().add(savedReport);
            patientsRepository.save(patient);
            return savedReport;
        } else {
            // Hastayı bulamazsa null döndür
            return null;
        }
    }
}
