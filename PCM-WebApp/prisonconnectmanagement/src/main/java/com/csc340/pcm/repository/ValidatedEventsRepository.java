//Created by Marcus Harrington
//Last updated 11/14/22
//This File uses Vaadin to store and retrieve data via the ValidatedEventsService file
package com.csc340.pcm.repository;

import com.csc340.pcm.entity.ValidatedEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidatedEventsRepository extends JpaRepository<ValidatedEvents, UUID> {
}
