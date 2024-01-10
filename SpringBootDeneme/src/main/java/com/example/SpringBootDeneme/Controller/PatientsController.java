package com.example.SpringBootDeneme.Controller;


import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import com.example.SpringBootDeneme.Repository.PatientsRepository;
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

    @GetMapping(path = "/seeAllPatients")
    public Iterable<Patients> getAllPatients() {return PatientsRepository.findAll(); }

    @GetMapping("/patient/{patientId}")
    public List<Patients> getPatients(@PathVariable Integer patientId) {
        return PatientsRepository.findByPatientId(patientId);
    }




    @PostMapping(path ="/createPatient/")
    public Patients post(@RequestBody Patients patients)
    {
        return PatientsRepository.save(patients);
    }
}