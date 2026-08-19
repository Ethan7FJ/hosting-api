package com.example.hostingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostingapi.dto.DomainRequest;
import com.example.hostingapi.entity.Domain;
import com.example.hostingapi.service.DomainService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/domains")
public class DomainController {
    
    private final DomainService domainService;

    public DomainController(DomainService domainService){
        this.domainService = domainService;
    }

    @GetMapping
    public ResponseEntity<List<Domain>> getAllDomains(){
        return ResponseEntity.ok(domainService.getAllDomains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domain> getDomainById(@PathVariable Long id){
        return ResponseEntity.ok(domainService.getDomainById(id));
    }

    @PostMapping
    public ResponseEntity<Domain> createDomain(@Valid @RequestBody DomainRequest request){
        return ResponseEntity.ok(domainService.createDomain(request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable Long id, @Valid @RequestBody DomainRequest request){
        return ResponseEntity.ok(domainService.updateDomain(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        domainService.deleteDomain(id);
        return ResponseEntity.noContent().build();
    }
}
