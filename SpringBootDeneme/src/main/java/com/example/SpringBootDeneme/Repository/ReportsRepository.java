package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
    List<Reports> findByRapNum(long rapNum);
    List<Reports> findByPatient_PatientId(Long patientId);
}
