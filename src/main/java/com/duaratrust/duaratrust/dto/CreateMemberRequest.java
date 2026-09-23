package com.duaratrust.duaratrust.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateMemberRequest {

    @NotBlank(message = "Member name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private Long groupId;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
