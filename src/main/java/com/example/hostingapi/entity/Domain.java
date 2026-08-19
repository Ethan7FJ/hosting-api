package com.example.hostingapi.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "domains")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "hosting_plan_id", nullable = false)
    private HostingPlan hostingPlan;

    public Domain() {
    }

    public Domain(String name, LocalDate expirationDate, Client client, HostingPlan hostingPlan) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.client = client;
        this.hostingPlan = hostingPlan;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Client getClient() {
        return client;
    }

    public HostingPlan getHostingPlan() {
        return hostingPlan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setHostingPlan(HostingPlan hostingPlan) {
        this.hostingPlan = hostingPlan;
    }
}
