package com.example.SpringBootDeneme.Controller;


import com.example.SpringBootDeneme.Entity.Admin;
import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Entity.Reports;
import com.example.SpringBootDeneme.Repository.AdminRepository;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository AdminRepository;

    @Autowired
    private DoctorRepository DoctorRepository;

    @PostMapping("/login")
    public String login(@RequestBody Admin admins) {
        String adminName = admins.getAdminName();
        String password = admins.getAdminPassword();

        Optional<Admin> admin = AdminRepository.findByAdminName(adminName);

        if (admin.isPresent() && admin.get().getAdminPassword().equals(password)) {
            return String.valueOf(admin.get().getId());
        } else {
            return "Invalid credentials!";
        }
    }

    @GetMapping(path ="/seeAllDoctors")
    public Iterable<Doctor> getAllDoctors(){ return DoctorRepository.findAll(); }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        Optional<Doctor> doctor = DoctorRepository.findById(id);
        if (doctor.isPresent()) return doctor.get();
        return new Doctor();
    }

    @PostMapping(path ="/createDoctor/{id}")
    public Doctor post(@RequestBody Doctor doctor) {return DoctorRepository.save(doctor); }

}