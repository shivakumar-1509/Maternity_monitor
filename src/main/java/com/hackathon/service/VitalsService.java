package com.hackathon.service;

import com.hackathon.DTO.AIResponseDTO;
import com.hackathon.DTO.NurseVitalsRequest;

public interface VitalsService {

    AIResponseDTO addVitals(NurseVitalsRequest request);

}
