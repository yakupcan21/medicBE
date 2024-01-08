package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
    Optional<Patients> findByPatientName(String patientName);
}

