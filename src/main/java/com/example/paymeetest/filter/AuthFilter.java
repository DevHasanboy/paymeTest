package com.example.paymeetest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

public class AuthFilter extends OncePerRequestFilter {
    private static final String MERCHANT_ID = "your-secret-key";
    private static final String API_KEY = "merchant-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        String base64Credentials = authHeader.substring(6);
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        if (!credentials.equals(MERCHANT_ID + ":" + API_KEY)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

