package com.api.rms.configs;

import com.api.rms.utilities.ResponseWriter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final ResponseWriter responseWriter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {
            List<String> ignoreApis = List.of("/api/v1/authenticate");
            if (ignoreApis.contains(request.getServletPath())) {
                filterChain.doFilter(request, response);
                return;
            }

            if (authHeader != null && authHeader.startsWith("Bearer")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                            null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            responseWriter.writeResponse(response, 412, "Token Expired!");
        } catch (Exception e) {
            responseWriter.writeResponse(response, 500, "Something went wrong!");
        }
    }
}
