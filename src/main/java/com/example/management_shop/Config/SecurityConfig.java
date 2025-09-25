    package com.example.management_shop.Config;


    import com.example.management_shop.Sercurity.jwt.AuthTokenFilter;
    import com.example.management_shop.Sercurity.Services.UserDetailsServiceImpl;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.*;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.*;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @RequiredArgsConstructor
    @EnableMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {

        private final AuthTokenFilter authTokenFilter;
        private final UserDetailsServiceImpl userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }

