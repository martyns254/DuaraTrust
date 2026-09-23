package com.duaratrust.duaratrust.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateWithdrawalRequest {
    @NotNull private Long groupId;
    @NotNull private Long requestedByMemberId;
    @Positive private Double amount;
    private String reason;

    public @NotNull Long getGroupId() {
        return groupId;
    }

    public void setGroupId(@NotNull Long groupId) {
        this.groupId = groupId;
    }

    public @NotNull Long getRequestedByMemberId() {
        return requestedByMemberId;
    }

    public void setRequestedByMemberId(@NotNull Long requestedByMemberId) {
        this.requestedByMemberId = requestedByMemberId;
    }

    public @Positive Double getAmount() {
        return amount;
    }

    public void setAmount(@Positive Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
