package com.hackathon.controller;


import com.hackathon.model.Patient;
import com.hackathon.service.JwtService;
import com.hackathon.service.PatientService;
import com.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/patient/")
public class PatientController {

    @Autowired
    private UserService userService;

    private PatientService patientService;

    @Autowired
    private JwtService jwtService;

//    @PostMapping("/login")
//    public LoginResponse patientLogin(@RequestBody PatientLogin request) {
//
//        User user = userService.authenticate(
//        );
//
//        if (!user.getRole().name().equals("PATIENT")) {
//            throw new RuntimeException("Access denied. Not a patient.");
//        }
//
//        String token = jwtService.generateToken(user.getUsername());
//
//        return new LoginResponse(token, user.getRole().name());
//    }

    @PreAuthorize("hasRole('NURSE')")
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }




}

