package com.example.SpringBootDeneme.Repository;

import com.example.SpringBootDeneme.Entity.CreateReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.LongFunction;

public interface CreateReportRepository extends JpaRepository<CreateReport, Long> {
    /*List<CreateReport> findBySomeCriteria(String criteria);*/
}
