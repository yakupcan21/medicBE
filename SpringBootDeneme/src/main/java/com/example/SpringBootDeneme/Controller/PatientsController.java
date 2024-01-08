package com.example.SpringBootDeneme.Controller;

import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import com.example.SpringBootDeneme.Repository.PatientsRepository;
import com.example.SpringBootDeneme.Entity.AuthRequest;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Service.JwtService;
import com.example.SpringBootDeneme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping({"/patients", "/auth"})
public class PatientsController {

    @Autowired
    private PatientsRepository PatientsRepository;

    @Autowired
    private PatientsService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DoctorRepository doctorRepository; // Add this if not already present

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping(path = "/seeAllPatients")
    public Iterable<Patients> getAllPatients() {
        return PatientsRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patients getPatients(@PathVariable Long id) {
        Optional<Patients> patients = PatientsRepository.findById(id);
        return patients.orElseGet(Patients::new);
    }

    @PostMapping(path = "/createPatient/{id}")
    public Patients post(@RequestBody Patients patients) {
        return PatientsRepository.save(patients);
    }

    // Merge the methods from UserController
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Patients patients) {
        return service.addUser(patients);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
