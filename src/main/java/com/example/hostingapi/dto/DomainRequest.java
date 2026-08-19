package com.example.hostingapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DomainRequest {
    
    @NotBlank(message = "El nombre del dominio es obligatorio")
    private String name;

    @NotNull(message = "La fecha de expiracion es obligatoria")
    @Future(message = "La fecha de expiracion debe se futura")
    private LocalDate expirationDate;

    @NotNull(message = "El cliente es obligatorio")
    private Long clientId;

    @NotNull(message = "El plan de hosting es obligatorio")
    private Long hostingPlanId;

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getHostingPlanId() {
        return hostingPlanId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(LocalDate expiration_date) {
        this.expirationDate = expiration_date;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setHostingPlanId(Long hosting_planId) {
        this.hostingPlanId = hosting_planId;
    }
}
