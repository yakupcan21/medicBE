package com.example.SpringBootDeneme.Service;

import com.example.SpringBootDeneme.Entity.Patients;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PatientsDetails implements UserDetails {

    private String name;
    private String password;

    public PatientsDetails(Patients patients) {
        name = patients.getPatientName();
        password = patients.getPatientPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // If you don't have roles, return an empty list or any default authorities
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
