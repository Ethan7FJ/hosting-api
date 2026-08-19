package com.example.hostingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostingapi.entity.HostingPlan;
import com.example.hostingapi.service.HostingPlanService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/hostingplan")
public class HostingPlanController {
    private final HostingPlanService hostingPlanService;

    public HostingPlanController(HostingPlanService hostingPlanService) {
        this.hostingPlanService = hostingPlanService;
    }

    @GetMapping
    public ResponseEntity<List<HostingPlan>> getAllHostingPlans() {
        return ResponseEntity.ok(hostingPlanService.getAllHostingPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostingPlan> getHostingPlanById(@PathVariable Long id) {
        return hostingPlanService.getHostingPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HostingPlan> createHostingPlan(@RequestBody HostingPlan hostingPlan) {
        return ResponseEntity.ok(hostingPlanService.createHostingPlan(hostingPlan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostingPlan> updateHostingPlan(@PathVariable Long id, @RequestBody HostingPlan hostingPlan) {
        return ResponseEntity.ok(hostingPlanService.updateHostingPlan(id, hostingPlan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHostingPlan(@PathVariable Long id) {
        hostingPlanService.deleteHostingPlan(id);
        return ResponseEntity.noContent().build();
    }

}
