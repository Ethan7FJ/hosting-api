package com.example.hostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostingapi.entity.Domain;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    
}
