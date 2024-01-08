package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
}
