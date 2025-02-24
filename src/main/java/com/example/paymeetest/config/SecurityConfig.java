package com.example.paymeetest.config;

import com.example.paymeetest.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF ni o'chiramiz
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/payme/**").permitAll() // Payme endpointlariga ruxsat berish
                        .anyRequest().authenticated() // Boshqa barcha so'rovlar autentifikatsiyadan o'tishi kerak
                )
                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class) // AuthFilter'ni qo'shish
                .httpBasic(AbstractHttpConfigurer::disable) // Basic Auth ni o'chirish
                .formLogin(AbstractHttpConfigurer::disable); // Login formani o'chirish

        return http.build();
    }
}





