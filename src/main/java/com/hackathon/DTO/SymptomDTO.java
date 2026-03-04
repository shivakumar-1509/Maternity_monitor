package com.hackathon.DTO;

import com.hackathon.model.SeverityLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SymptomDTO {
    private String name;
    private SeverityLevel severity;

}
