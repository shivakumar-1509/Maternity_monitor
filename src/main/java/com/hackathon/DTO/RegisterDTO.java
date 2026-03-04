package com.hackathon.DTO;

import com.hackathon.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private Role role;
}
