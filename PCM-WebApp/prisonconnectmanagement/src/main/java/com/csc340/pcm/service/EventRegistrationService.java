package com.csc340.pcm.service;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.repository.PendingEventRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    private final PendingEventRegistrationRepository pendingEventRegistrationRepository;

    public EventRegistrationService(PendingEventRegistrationRepository pendingEventRegistrationRepository) {
        this.pendingEventRegistrationRepository = pendingEventRegistrationRepository;
    }

    public long countEvent() {
        return pendingEventRegistrationRepository.count();
    }

    public void deleteEvent(PendingEventRegistration pendingEventRegistration) {
        pendingEventRegistrationRepository.delete(pendingEventRegistration);
    }

    public void saveEvent(PendingEventRegistration pendingEventRegistration) {
        if (pendingEventRegistration == null) {
            System.err.println("Event is null. Are you sure you have connected your form to the application?");
            return;
        }
        pendingEventRegistrationRepository.save(pendingEventRegistration);
    }

    public List<PendingEventRegistration> findAllEvents() {
        return pendingEventRegistrationRepository.findAll();
    }


}
