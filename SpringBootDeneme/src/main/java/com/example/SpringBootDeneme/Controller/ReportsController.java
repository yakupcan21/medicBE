package com.example.SpringBootDeneme.Controller;

import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Reports;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import com.example.SpringBootDeneme.Repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsRepository ReportsRepository;

    @GetMapping(path ="/seeAllReports")
    public Iterable<Reports> getAllDoctors(){ return ReportsRepository.findAll(); }

    @GetMapping("/report/{id}")
    public Reports getReport(@PathVariable Long id) {
        Optional<Reports> report = ReportsRepository.findById(id);
        if (report.isPresent()) return report.get();
        return new Reports();
    }

    @PostMapping(path ="/createReport/{id}")
    public Reports post(@RequestBody Reports reports) {return ReportsRepository.save(reports); }
}
