package com.qnadeel.springdemo.security.bookrentalsystem.configration;

import com.qnadeel.springdemo.security.bookrentalsystem.filter.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public Authentication Endpoints
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/registration",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Public Book Access
                        .requestMatchers(
                                "/api/books",
                                "/api/books/",
                                "/api/books/{bookId}",
                                "/api/books/book-by-genre",
                                "/api/books/book-by-author",
                                "/api/books/book-by-name"
                        ).permitAll()

                        // ADMIN-only Book Write Operations
                        .requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/books/{bookId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/{bookId}").hasRole("ADMIN")

                        // Rentals (Authenticated users)
                        .requestMatchers(
                                "/api/rentals/{userId}",
                                "/api/rentals/{rentalId}/return"
                        ).authenticated()

                        // Rentals (Admin access)
                        .requestMatchers(
                                "/api/rentals",
                                "/api/rentals/",
                                "/api/rentals/overdue",
                                "/api/rentals/during-rental"
                        ).hasRole("ADMIN")

                        // Users (Authenticated user's own profile)
                        .requestMatchers("/api/users/me").authenticated()

                        // Users (Admin-only user management)
                        .requestMatchers(
                                "/api/users/",
                                "/api/users/{userId}",
                                "/api/users/{userId}/rentals",
                                HttpMethod.PUT.name(), "/api/users/{userId}"
                        ).hasRole("ADMIN")

                        // Catch-all
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}