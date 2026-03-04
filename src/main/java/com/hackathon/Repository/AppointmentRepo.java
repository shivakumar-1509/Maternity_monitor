package com.hackathon.Repository;

import com.hackathon.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {


}
