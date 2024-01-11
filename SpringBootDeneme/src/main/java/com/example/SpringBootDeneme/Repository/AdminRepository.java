package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Admin;
import com.example.SpringBootDeneme.Entity.CreateReport;
import com.example.SpringBootDeneme.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminName(String adminName);

}
