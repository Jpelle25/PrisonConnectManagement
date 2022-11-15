package com.csc340.pcm.repository;

import com.csc340.pcm.entity.ValidatedSchedules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidatedSchedulesRepository extends JpaRepository<ValidatedSchedules, UUID> {
}
