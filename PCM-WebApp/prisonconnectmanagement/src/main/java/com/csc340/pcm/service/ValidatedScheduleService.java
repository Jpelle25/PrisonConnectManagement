package com.csc340.pcm.service;

import com.csc340.pcm.entity.ValidatedSchedules;
import com.csc340.pcm.repository.ValidatedSchedulesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatedScheduleService {

    private final ValidatedSchedulesRepository validatedSchedulesRepository;

    public ValidatedScheduleService(ValidatedSchedulesRepository validatedSchedulesRepository){
        this.validatedSchedulesRepository = validatedSchedulesRepository;
    }

    public List<ValidatedSchedules> findAllEvents() {
        return validatedSchedulesRepository.findAll();
    }
}
