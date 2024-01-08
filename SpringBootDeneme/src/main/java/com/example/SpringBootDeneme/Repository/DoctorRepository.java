package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
