package com.duaratrust.duaratrust.controller;

import com.duaratrust.duaratrust.dto.CreateGroupRequest;
import com.duaratrust.duaratrust.entity.Member;
import com.duaratrust.duaratrust.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@Valid @RequestBody CreateGroupRequest request) {
        return memberService.createMember(request);
    }

}
