package com.duaratrust.duaratrust.controller;

import com.duaratrust.duaratrust.dto.CreateGroupRequest;
import com.duaratrust.duaratrust.entity.Group;
import com.duaratrust.duaratrust.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group createGroup(@Valid @RequestBody CreateGroupRequest request){
        return groupService.createGroup(request);
    }
}
