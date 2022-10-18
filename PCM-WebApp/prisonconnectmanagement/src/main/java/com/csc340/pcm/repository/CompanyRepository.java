package com.csc340.pcm.repository;

import com.csc340.pcm.entity.Company;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
