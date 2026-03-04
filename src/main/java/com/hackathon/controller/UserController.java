package com.hackathon.controller;

import com.hackathon.DTO.NurseLogin;
import com.hackathon.DTO.RegisterDTO;

import com.hackathon.model.User;
import com.hackathon.service.JwtService;
import com.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;



    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService,AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    @PostMapping("/userlogin")
    public String nurseLogin(@RequestBody NurseLogin request) {
        System.out.println("nurseLogin");
        Authentication authentication =authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(request.getUsername());
        else return "Login Failed";

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO request) {

        User savedUser = userService.registerUser(request);

        return ResponseEntity.ok("User registered successfully with ID: "
                + savedUser.getId());
    }
}