package com.csc340.pcm.service;

import com.csc340.pcm.entity.Event;
import com.csc340.pcm.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //Methods Commented for future database usage

//    public long countEvent() {
//        return eventRepository.count();
//    }
//
//    public void deleteEvent(Event event) {
//        eventRepository.delete(event);
//    }
//
//    public void saveEvent(Event event) {
//        if (event == null) {
//            System.err.println("Event is null. Are you sure you have connected your form to the application?");
//            return;
//        }
//        eventRepository.save(event);
//    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }


}
