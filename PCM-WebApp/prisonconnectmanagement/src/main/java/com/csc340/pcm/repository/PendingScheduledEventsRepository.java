package com.csc340.pcm.repository;

import com.csc340.pcm.entity.PendingScheduledEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PendingScheduledEventsRepository extends JpaRepository<PendingScheduledEvents, UUID> {


}
