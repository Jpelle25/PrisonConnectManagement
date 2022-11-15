package com.csc340.pcm.service;

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
}
