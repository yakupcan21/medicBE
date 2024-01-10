package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
    List<Patients> findByPatientId(Integer patientId);
}
