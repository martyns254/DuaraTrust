package com.duaratrust.duaratrust.service;

import com.duaratrust.duaratrust.dto.ApproveWithdrawalRequest;
import com.duaratrust.duaratrust.dto.CreateWithdrawalRequest;
import com.duaratrust.duaratrust.entity.Approval;
import com.duaratrust.duaratrust.entity.Group;
import com.duaratrust.duaratrust.entity.WithdrawalRequest;
import com.duaratrust.duaratrust.repository.ApprovalRepository;
import com.duaratrust.duaratrust.repository.GroupRepository;
import com.duaratrust.duaratrust.repository.WithdrawalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private GroupRepository groupRepository;

    public WithdrawalRequest createWithdrawal(CreateWithdrawalRequest request) {
        WithdrawalRequest wr = new WithdrawalRequest();
        wr.setGroupId(request.getGroupId());
        wr.setRequestedByMemberId(request.getRequestedByMemberId());
        wr.setAmount(request.getAmount());
        wr.setReason(request.getReason());
        wr.setStatus("PENDING");
        wr.setCreatedAt(LocalDateTime.now());
        return withdrawalRequestRepository.save(wr);
    }
    public WithdrawalRequest getWithdrawal(Long id) {
        return withdrawalRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Withdrawal request not found"));
    }

    public WithdrawalRequest submitApproval(Long withdrawalId, ApproveWithdrawalRequest request) {
        WithdrawalRequest wr = withdrawalRequestRepository.findById(withdrawalId)
                .orElseThrow(() -> new IllegalArgumentException("Withdrawal request not found"));
        if (!wr.getStatus().equals("PENDING")) {
            throw new IllegalStateException("This withdrawal is no longer pending");
        }

        Approval existing = approvalRepository.findByWithdrawalRequestIdAndMemberId(withdrawalId, request.getMemberId());
        if (existing != null) {
            throw new IllegalStateException("This member has already voted on this request");
        }

        Approval approval = new Approval();
        approval.setWithdrawalRequestId(withdrawalId);
        approval.setMemberId(request.getMemberId());
        approval.setDecision(request.getDecision());
        approval.setDecidedAt(LocalDateTime.now());
        approvalRepository.save(approval);

        if (request.getDecision().equals("REJECT")) {
            wr.setStatus("REJECTED");
            withdrawalRequestRepository.save(wr);
            return wr;
        }

        List<Approval> approvals = approvalRepository.findByWithdrawalRequestId(withdrawalId);
        long approveCount = approvals.stream().filter(a -> a.getDecision().equals("APPROVE")).count();

        Group group = groupRepository.findById(wr.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        if (approveCount >= group.getApprovalThreshold()) {
            wr.setStatus("APPROVED");
            withdrawalRequestRepository.save(wr);
        }

        return wr;

    }
    public List<WithdrawalRequest> getAll() {
        return withdrawalRequestRepository.findAll();
    }


}
