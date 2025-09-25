package com.example.management_shop.Payload.Request;

import lombok.*;

@Getter @Setter
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String phone;
}

