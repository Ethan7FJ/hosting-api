package com.example.hostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostingapi.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
