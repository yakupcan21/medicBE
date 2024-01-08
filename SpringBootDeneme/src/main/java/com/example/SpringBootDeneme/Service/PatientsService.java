package com.example.SpringBootDeneme.Service;

import com.example.SpringBootDeneme.Repository.PatientsRepository;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientsService implements UserDetailsService {

    @Autowired
    private PatientsRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Patients> userDetail = repository.findByPatientName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(PatientsDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(Patients patients) {
        patients.setPatientPassword(encoder.encode(patients.getPatientPassword()));
        repository.save(patients);
        return "User Added Successfully";
    }
}
