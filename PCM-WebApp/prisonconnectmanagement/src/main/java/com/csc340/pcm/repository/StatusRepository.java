package com.csc340.pcm.repository;

import com.csc340.pcm.entity.Status;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, UUID> {

}
