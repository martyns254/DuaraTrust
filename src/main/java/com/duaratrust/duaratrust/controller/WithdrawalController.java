package com.duaratrust.duaratrust.controller;

import com.duaratrust.duaratrust.dto.ApproveWithdrawalRequest;
import com.duaratrust.duaratrust.dto.CreateWithdrawalRequest;
import com.duaratrust.duaratrust.entity.WithdrawalRequest;
import com.duaratrust.duaratrust.service.WithdrawalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping
    public WithdrawalRequest create(@Valid @RequestBody CreateWithdrawalRequest request) {
        return withdrawalService.createWithdrawal(request);
    }

    @PostMapping("/{id}/approvals")
    public WithdrawalRequest approve(@PathVariable Long id, @Valid @RequestBody ApproveWithdrawalRequest request) {
        return withdrawalService.submitApproval(id, request);
    }

    @GetMapping
    public List<WithdrawalRequest> getAll() {
        return withdrawalService.getAll();
    }

    @GetMapping("/{id}")
    public WithdrawalRequest getOne(@PathVariable Long id) {
        return withdrawalService.getWithdrawal(id);
    }

}
