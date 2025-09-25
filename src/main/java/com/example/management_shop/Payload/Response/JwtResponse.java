package com.example.management_shop.Payload.Response;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String fullName;
}

