package com.hackathon.service;


import com.hackathon.DTO.NurseLogin;
import com.hackathon.DTO.RegisterDTO;

import com.hackathon.Repository.PatientRepo;
import com.hackathon.Repository.UserRepo;
import com.hackathon.model.Nurse;
import com.hackathon.model.Patient;
import com.hackathon.model.Role;
import com.hackathon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    // Used when creating nurse or patient account
    public User registerUser(RegisterDTO request) {


        // 1️⃣ Check if username already exists
        if (userRepo.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // 2️⃣ Convert role String → Enum (recommended)
        Role role;
        try {
            role = Role.valueOf(request.getRole().toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role provided");
        }

        // 3️⃣ Create user entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        // 4️⃣ Save user


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    // Used for login
    public User authenticate(NurseLogin loginDTO) {

        User user = userRepo.findByUsername(loginDTO.getUsername());

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }


}
