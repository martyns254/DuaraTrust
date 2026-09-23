package com.duaratrust.duaratrust.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "withdraw_requests")
public class WithdrawalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private Long requestedByMemberId;
    private Double amount;
    private String reason;
    private String status;
    private LocalDateTime createdAt;

    public WithdrawalRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRequestedByMemberId() {
        return requestedByMemberId;
    }

    public void setRequestedByMemberId(Long requestedByMemberId) {
        this.requestedByMemberId = requestedByMemberId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
