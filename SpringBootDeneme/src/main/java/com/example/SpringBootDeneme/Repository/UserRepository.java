package com.example.SpringBootDeneme.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootDeneme.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    // Change method names to reflect the changes
    Boolean existsByPhoneNumber(long phoneNumber);

    // Update 'findById' to use 'Optional<User> findByPhoneNumber(Long phoneNumber);'
    Optional<User> findByPhoneNumber(Long phoneNumber);
}
