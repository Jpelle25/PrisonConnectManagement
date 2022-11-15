package com.csc340.pcm.repository;

import com.csc340.pcm.entity.ValidatedEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidatedEventsRepository extends JpaRepository<ValidatedEvents, UUID> {
}
