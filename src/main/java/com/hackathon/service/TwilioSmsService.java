package com.hackathon.service;

import com.hackathon.model.Appointment;
import com.hackathon.model.Nurse;
import com.hackathon.model.Patient;
import com.hackathon.model.SeverityLevel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TwilioSmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String toNumber, String messageBody) {

        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                messageBody
        ).create();
    }

    public void notifyNurse(Patient patient,
                            SeverityLevel severity,
                            LocalDate appointmentDate) {

        Nurse nurse = patient.getNurse();
        String nursePhone = nurse.getPhoneNumber();

        String message = "ALERT: Patient "
                + patient.getName()
                + " (" + patient.getUser().getUsername() + ") reported "
                + severity
                + " symptoms. Appointment scheduled on "
                + appointmentDate;

        // ✅ Direct method call (NO self injection)
        sendSms(nursePhone, message);
    }
}
