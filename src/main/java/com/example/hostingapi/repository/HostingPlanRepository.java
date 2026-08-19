package com.example.hostingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostingapi.entity.HostingPlan;

public interface HostingPlanRepository extends JpaRepository<HostingPlan, Long> {
}
