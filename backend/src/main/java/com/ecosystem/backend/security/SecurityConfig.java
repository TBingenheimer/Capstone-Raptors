package com.ecosystem.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(a->a
                .anyRequest().permitAll()
            )
            .logout(l->l.logoutSuccessUrl("http://localhost:5173"))
            .oauth2Login(o->o.defaultSuccessUrl("http://localhost:5173"));
        return http.build();
    }
}
/*
.requestMatchers("/api/auth/me").authenticated()
.requestMatchers("/api/secured").authenticated()
.requestMatchers(HttpMethod.POST,"/api/x").authenticated()
.requestMatchers(HttpMethod.PUT,"/api/x").authenticated()
.requestMatchers(HttpMethod.DELETE,"/api/x").authenticated()
.requestMatchers("/api/x").hasAuthority("ADMIN")
*/