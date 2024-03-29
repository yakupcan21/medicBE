package com.example.SpringBootDeneme.Controller;

import com.example.SpringBootDeneme.Entity.CreateReport;
import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Repository.CreateReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/createReport")
public class CreateReportController {

    @Autowired
    private CreateReportRepository CreateReportRepository;

    @GetMapping(path ="/seeAllCreateReports")
    public Iterable<CreateReport> getAllCreateReports(){ return CreateReportRepository.findAll(); }

    @GetMapping("/createReportsByRapNum/{rapNum}")
    public List<CreateReport> getCreateReportsByRapNum(@PathVariable long rapNum) {
        return CreateReportRepository.findByReports_RapNum(rapNum);
    }

    @GetMapping("/createReports/{id}")
    public CreateReport getCreateReports(@PathVariable Long id) {
        Optional<CreateReport> reports = CreateReportRepository.findById(id);
        if (reports.isPresent()) return reports.get();
        return new CreateReport();
    }

    @PostMapping(path ="/createReports")
    public CreateReport post(@RequestBody CreateReport createreport) {return CreateReportRepository.save(createreport); }

}
