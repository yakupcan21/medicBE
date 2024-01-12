package com.example.SpringBootDeneme.Controller;
import com.example.SpringBootDeneme.Entity.Doctor;
import com.example.SpringBootDeneme.Entity.Patients;
import com.example.SpringBootDeneme.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository DoctorRepository;

    @GetMapping(path ="/seeAllDoctors")
    public Iterable<Doctor> getAllDoctors(){ return DoctorRepository.findAll(); }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        Optional<Doctor> doctor = DoctorRepository.findById(id);
        if (doctor.isPresent()) return doctor.get();
        return new Doctor();
    }

    @PostMapping(path ="/createDoctor/{id}")
    public Doctor post(@RequestBody Doctor doctor) {return DoctorRepository.save(doctor); }

    @PostMapping("/login")
    public String login(@RequestBody Doctor doctors) {
        Long docId = doctors.getDocId();
        String password = doctors.getDocPassword();

        Optional<Doctor> doctor = DoctorRepository.findById(docId);

        if (doctor.isPresent() && doctor.get().getDocPassword().equals(password)) {
            return String.valueOf(doctors.getDocId());
        } else {
            return "Invalid credentials!";
        }
    }
}


