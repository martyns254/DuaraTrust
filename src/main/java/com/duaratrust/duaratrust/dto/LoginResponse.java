package com.duaratrust.duaratrust.dto;

public class LoginResponse {

    private String token;
    private String phoneNumber;
    private String role;

    public LoginResponse(String token, String phoneNumber, String role) {
        this.token = token;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

}
