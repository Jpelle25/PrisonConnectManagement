package com.csc340.pcm.repository;

import com.csc340.pcm.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrisonerRepository extends JpaRepository<Prisoner, UUID>{
}
