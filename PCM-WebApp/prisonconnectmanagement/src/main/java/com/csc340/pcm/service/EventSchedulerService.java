package com.csc340.pcm.service;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.repository.PendingScheduledEventsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventSchedulerService {

    private final PendingScheduledEventsRepository pendingScheduledEventsRepository;

    public EventSchedulerService(PendingScheduledEventsRepository pendingScheduledEventsRepository) {
        this.pendingScheduledEventsRepository = pendingScheduledEventsRepository;
    }

    public void saveEvent(PendingScheduledEvents pendingScheduledEvents) {
        if (pendingScheduledEvents == null) {
            System.err.println("Event is null. Are you sure you have connected your form to the application?");
            return;
        }
        pendingScheduledEventsRepository.save(pendingScheduledEvents);
    }

    public List<PendingScheduledEvents> findAllCompleteEvents() {
        return pendingScheduledEventsRepository.findAll();
    }
}
