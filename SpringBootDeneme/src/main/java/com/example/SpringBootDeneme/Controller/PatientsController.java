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
@RequestMapping("/patients")
public class PatientsController {


    @Autowired
    private PatientsRepository PatientsRepository;

    @Autowired
    private ReportsRepository ReportsRepository;

    @GetMapping(path = "/seeAllPatients")
    public Iterable<Patients> getAllPatients() {
        List<Patients> patientsList = PatientsRepository.findAll();


        for (Patients patient : patientsList) {
            List<Reports> reports = ReportsRepository.findByPatient_PatientId(patient.getPatientId());

            // Hastanın rapor bilgilerini güncelle
            patient.updateReportInformation();
        }


        return patientsList;
    }




    @GetMapping("/patient/{patientId}")
    public List<Patients> getPatients(@PathVariable Integer patientId) {
        List<Patients> patientsList = PatientsRepository.findByPatientId(patientId);

        if (!patientsList.isEmpty()) {
            Patients patient = patientsList.get(0); // Varsayılan olarak sadece ilk hastayı alıyoruz

            // Hastanın raporlarını bul
            List<Reports> reports = ReportsRepository.findByPatient_PatientId(patientId);

            // Hastanın rapor listesine bulunan raporları ekleyin
            patient.setReports(reports);


            // Hastanın rapor bilgilerini güncelle
            patient.updateReportInformation();

        }

        return patientsList;
    }




    @PostMapping(path ="/createPatient/")
    public Patients post(@RequestBody Patients patients)
    {
        return PatientsRepository.save(patients);
    }
}