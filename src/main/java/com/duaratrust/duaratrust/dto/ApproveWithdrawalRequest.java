package com.duaratrust.duaratrust.dto;

import jakarta.validation.constraints.NotNull;

public class ApproveWithdrawalRequest {
    @NotNull private Long memberId;
    @NotNull
    private String decision;

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
}
