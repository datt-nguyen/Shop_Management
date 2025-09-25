package com.example.management_shop.Sercurity.jwt;


import com.example.management_shop.Sercurity.Services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {

        String path = request.getServletPath();

        // Bỏ qua filter cho login, register
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String header = request.getHeader("Authorization");
            String token = null;
            if (header != null && header.startsWith("Bearer ")) token = header.substring(7);
            if (token != null && jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println(">>> Username: " + username);
                System.out.println(">>> Authorities: " + userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ex) {
            // ignore authentication error (request will be unauthorized if needed)
        }
        filterChain.doFilter(request, response);
    }
}

