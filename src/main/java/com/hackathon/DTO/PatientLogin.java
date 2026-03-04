package com.hackathon.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PatientLogin {
    String username;
    String password;
}
