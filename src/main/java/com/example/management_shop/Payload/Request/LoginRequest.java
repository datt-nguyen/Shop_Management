package com.example.management_shop.Payload.Request;


import lombok.*;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}
