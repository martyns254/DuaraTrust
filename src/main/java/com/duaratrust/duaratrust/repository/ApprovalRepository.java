package com.duaratrust.duaratrust.repository;

import com.duaratrust.duaratrust.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    List<Approval> findByWithdrawalRequestId(Long withdrawalRequestId);
    Approval findByWithdrawalRequestIdAndMemberId(Long withdrawalRequestId, Long memberId);
}
