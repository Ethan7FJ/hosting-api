package com.example.hostingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hostingapi.entity.HostingPlan;
import com.example.hostingapi.exception.ResourceNotFoundException;
import com.example.hostingapi.repository.HostingPlanRepository;

@Service
public class HostingPlanService {
    private final HostingPlanRepository hostingPlanRepository;

    public HostingPlanService(HostingPlanRepository hostingPlanRepository) {
        this.hostingPlanRepository = hostingPlanRepository;
    }

    public List<HostingPlan> getAllHostingPlans() {
        return hostingPlanRepository.findAll();
    }

    public Optional<HostingPlan> getHostingPlanById(Long id) {
        return hostingPlanRepository.findById(id);
    }

    public HostingPlan createHostingPlan(HostingPlan hostingPlan) {
        return hostingPlanRepository.save(hostingPlan);
    }

    public HostingPlan updateHostingPlan(Long id, HostingPlan hostingPlan) {
        HostingPlan existHostingPlan = hostingPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HostingPlan no encontrado"));

        existHostingPlan.setName(hostingPlan.getName());
        existHostingPlan.setPrice(hostingPlan.getPrice());
        existHostingPlan.setStatus(hostingPlan.getStatus());

        return hostingPlanRepository.save(existHostingPlan);
    }

    public void deleteHostingPlan(Long id) {
        if (!hostingPlanRepository.existsById(id)) {
            throw new ResourceNotFoundException("HostingPlan no encontrado");
        }

        hostingPlanRepository.deleteById(id);
    }
}
