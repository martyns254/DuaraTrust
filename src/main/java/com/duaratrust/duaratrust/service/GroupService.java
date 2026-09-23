package com.duaratrust.duaratrust.service;


import com.duaratrust.duaratrust.dto.CreateGroupRequest;
import com.duaratrust.duaratrust.entity.Group;
import com.duaratrust.duaratrust.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(CreateGroupRequest request) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();

        Group group = new Group();
        group.setName(request.getName());
        group.setApprovalThreshold(request.getApprovalThreshold());
        group.setContributionAmount(request.getContributionAmount());
        group.setContributionFrequency(request.getContributionFrequency());
        group.setCreatedByPhoneNumber(phoneNumber);
        group.setCreatedAt(LocalDateTime.now());

        return groupRepository.save(group);
    }
}
