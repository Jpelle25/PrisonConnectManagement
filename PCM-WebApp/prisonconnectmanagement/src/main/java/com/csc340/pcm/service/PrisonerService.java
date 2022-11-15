package com.csc340.pcm.service;

import com.csc340.pcm.entity.Prisoner;
import com.csc340.pcm.repository.PrisonerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrisonerService {

    private final PrisonerRepository prisonerRepository;

    public PrisonerService(PrisonerRepository prisonerRepository) {
        this.prisonerRepository = prisonerRepository;
    }

    public List<Prisoner> findAllPrisoners() {
        return prisonerRepository.findAll();
    }

}