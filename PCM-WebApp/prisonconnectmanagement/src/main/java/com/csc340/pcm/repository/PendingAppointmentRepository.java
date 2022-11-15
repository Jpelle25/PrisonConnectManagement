package com.csc340.pcm.repository;

import com.csc340.pcm.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PendingAppointmentRepository extends JpaRepository<Appointment, UUID> {
}
