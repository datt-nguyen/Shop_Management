package com.example.management_shop.Controller;


import com.example.management_shop.Entity.Roles;
import com.example.management_shop.Entity.Users;
import com.example.management_shop.Repositories.RoleRepository;
import com.example.management_shop.Repositories.UserRepository;
import com.example.management_shop.Payload.Request.LoginRequest;
import com.example.management_shop.Payload.Request.RegisterRequest;
import com.example.management_shop.Payload.Response.JwtResponse;
import com.example.management_shop.Sercurity.jwt.JwtUtils;
import com.example.management_shop.Sercurity.Services.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test-password")
    public boolean testPassword() {
        String raw = "123456";
        String encoded = "$2a$10$DelXJZBzAWLGZcmPoL3bte1wYt3E7iZKzMBI9O4AE.QtV/GyN2Uoa";
        return passwordEncoder.matches(raw, encoded);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("Email already taken");
        }
        Users u = Users.builder()
                .fullname(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .address(req.getAddress())
                .phone(req.getPhone())
                .status(true)
                .build();

        Roles userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(Roles.builder().name("ROLE_USER").build()));
        u.setRoles(Collections.singleton(userRole));
        Users saved = userRepository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        log.info(">>> Login attempt with email: {}", req.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails ud = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(ud.getUsername());

        log.info(">>> Authentication success for user: {}", ud.getUsername());
        log.info(">>> Roles: {}", ud.getAuthorities());
        log.info(">>> Generated JWT: {}", jwt);

        return ResponseEntity.ok(
                new JwtResponse(jwt, "Bearer", ud.getUser().getId(),
                        ud.getUser().getEmail(),
                        ud.getUser().getFullname())
        );
    }

}

