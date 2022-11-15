package com.csc340.pcm.service;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.ValidatedEvents;
import com.csc340.pcm.repository.ValidatedEventsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatedEventsService {

    private final ValidatedEventsRepository validatedEventsRepository;

    public ValidatedEventsService(ValidatedEventsRepository validatedEventsRepository) {
        this.validatedEventsRepository = validatedEventsRepository;
    }

    public List<ValidatedEvents> findAllEvents() {
        return validatedEventsRepository.findAll();
    }
    public void saveEvent(ValidatedEvents validatedEvents){
        if (validatedEvents == null) {
            System.err.println("Event is null. Are you sure you have connected your form to the application?");
            return;
        }
        validatedEventsRepository.save(validatedEvents);
    }

    public void deleteEvent(ValidatedEvents validatedEvents) {
        validatedEventsRepository.delete(validatedEvents);
    }
}
