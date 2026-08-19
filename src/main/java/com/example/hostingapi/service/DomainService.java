package com.example.hostingapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hostingapi.dto.DomainRequest;
import com.example.hostingapi.entity.Client;
import com.example.hostingapi.entity.Domain;
import com.example.hostingapi.entity.HostingPlan;
import com.example.hostingapi.enums.HostingPlanStatus;
import com.example.hostingapi.exception.BusinessException;
import com.example.hostingapi.exception.ResourceNotFoundException;
import com.example.hostingapi.repository.ClientRepository;
import com.example.hostingapi.repository.DomainRepository;
import com.example.hostingapi.repository.HostingPlanRepository;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final ClientRepository clientRepository;
    private final HostingPlanRepository hostingPlanRepository;

    public DomainService(DomainRepository domainRepository, ClientRepository clientRepository,
            HostingPlanRepository hostingPlanRepository) {
        this.domainRepository = domainRepository;
        this.clientRepository = clientRepository;
        this.hostingPlanRepository = hostingPlanRepository;
    }

    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    public Domain getDomainById(Long id) {
        return domainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dominio no encontrado"));
    }

    public Domain createDomain(DomainRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        HostingPlan hostingPlan = hostingPlanRepository.findById(request.getHostingPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan de hosting no encontrado"));

        if (hostingPlan.getStatus() != HostingPlanStatus.Active) {
            throw new BusinessException(
                    "No se puede asociar un dominio a un plan de hosting inactivo.");
        }

        if (!request.getExpirationDate().isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha ingresada debe de ser futura");
        }

        Domain domain = new Domain(
                request.getName(),
                request.getExpirationDate(),
                client,
                hostingPlan);

        return domainRepository.save(domain);
    }

    public Domain updateDomain(Long id, DomainRequest request) {
        Domain existingDomain = domainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dominio no encontrado"));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));

        HostingPlan hostingPlan = hostingPlanRepository.findById(request.getHostingPlanId())
                .orElseThrow(()-> new ResourceNotFoundException("plan de hosting no encontrado"));

        if (hostingPlan.getStatus() != HostingPlanStatus.Active) {
            throw new BusinessException(
                    "No se puede asociar un dominio a un plan de hosting inactivo.");
        }

        if (!request.getExpirationDate().isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha ingresada debe de ser futura");
        }

        existingDomain.setName(request.getName());
        existingDomain.setExpirationDate(request.getExpirationDate());
        existingDomain.setClient(client);
        existingDomain.setHostingPlan(hostingPlan);

        return domainRepository.save(existingDomain);
    }

    public void deleteDomain(Long id){
        if(!domainRepository.existsById(id)){
            throw new ResourceNotFoundException("Dominio no encontrado");
        }

        domainRepository.deleteById(id);
    }

}
