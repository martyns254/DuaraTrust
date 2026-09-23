package com.duaratrust.duaratrust.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "groups_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer approvalThreshold;
    private Double contributionAmount;
    private String contributionFrequency;
    private String createdByPhoneNumber;
    private LocalDateTime createdAt;

    public Group() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApprovalThreshold() {
        return approvalThreshold;
    }

    public void setApprovalThreshold(Integer approvalThreshold) {
        this.approvalThreshold = approvalThreshold;
    }

    public Double getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(Double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public String getContributionFrequency() {
        return contributionFrequency;
    }

    public void setContributionFrequency(String contributionFrequency) {
        this.contributionFrequency = contributionFrequency;
    }

    public String getCreatedByPhoneNumber() {
        return createdByPhoneNumber;
    }

    public void setCreatedByPhoneNumber(String createdByPhoneNumber) {
        this.createdByPhoneNumber = createdByPhoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
