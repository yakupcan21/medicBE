package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
