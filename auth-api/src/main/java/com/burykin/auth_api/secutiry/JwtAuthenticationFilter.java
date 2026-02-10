package com.burykin.auth_api.secutiry;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = parseJwt(request);
        if (jwt != null && !jwt.isEmpty()) {
            String email = jwtUtil.extractEmail(jwt);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtUtil.isTokenValid(
                    jwt)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(email, null, List.of());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        final String authorizationString = "Authorization";
        final String bearerString = "Bearer ";
        String headerAuth = request.getHeader(authorizationString);
        if (headerAuth != null && headerAuth.startsWith(bearerString)) {
            return headerAuth.substring(bearerString.length());
        }
        return null;
    }
}
