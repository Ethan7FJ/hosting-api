package com.example.hostingapi.entity;

import com.example.hostingapi.enums.HostingPlanStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "hosting_plans")
public class HostingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private HostingPlanStatus status;

    public HostingPlan() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public HostingPlanStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStatus(HostingPlanStatus status) {
        this.status = status;
    }
}
