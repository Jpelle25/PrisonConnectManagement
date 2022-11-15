package com.csc340.pcm.service;

import com.csc340.pcm.entity.Appointment;
import com.csc340.pcm.repository.PendingAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final PendingAppointmentRepository pendingAppointmentRepository;

    public AppointmentService(PendingAppointmentRepository pendingAppointmentRepository) {
        this.pendingAppointmentRepository = pendingAppointmentRepository;
    }
    public List<Appointment> findAllEvents(){
        return pendingAppointmentRepository.findAll();
    }
    public void saveEvent(Appointment appointment){
        if(appointment.equals(null)){
            System.err.println("Event is null, are you sure you have connected your form to the application?");
            return;
        }
        pendingAppointmentRepository.save(appointment);
    }
}
