package com.duaratrust.duaratrust.dto;

public class UserResponse {

    private Long id;
    private String phoneNumber;
    private String role;

    public UserResponse(Long id, String phoneNumber, String role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }
}
