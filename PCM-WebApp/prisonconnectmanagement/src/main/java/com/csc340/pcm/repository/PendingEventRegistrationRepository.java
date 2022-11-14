package com.csc340.pcm.repository;

import com.csc340.pcm.entity.PendingEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PendingEventRegistrationRepository extends JpaRepository<PendingEventRegistration, UUID> {

}
