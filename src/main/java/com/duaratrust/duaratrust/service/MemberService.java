package com.duaratrust.duaratrust.service;

import com.duaratrust.duaratrust.dto.CreateMemberRequest;
import com.duaratrust.duaratrust.entity.Member;
import com.duaratrust.duaratrust.repository.GroupRepository;
import com.duaratrust.duaratrust.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(CreateMemberRequest request) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = new Member();
        member.setName(request.getName());

        return memberRepository.save(member);

    }

}
