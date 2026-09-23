package com.duaratrust.duaratrust.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateGroupRequest {

    @NotBlank(message = "group name is required")
    private String name;

    @Min(value = 1, message = "Approval threshold must be at least 1")
    private Integer approvalThreshold;

    @Positive(message = "Contribution amount must be positive")
    private Double contributionAmount;

    @NotBlank(message = "Contribution frequency is required")
    private String contributionFrequency;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getApprovalThreshold() { return approvalThreshold; }
    public void setApprovalThreshold(Integer approvalThreshold) { this.approvalThreshold = approvalThreshold; }
    public Double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(Double contributionAmount) { this.contributionAmount = contributionAmount; }
    public String getContributionFrequency() { return contributionFrequency; }
    public void setContributionFrequency(String contributionFrequency) { this.contributionFrequency = contributionFrequency; }
}

