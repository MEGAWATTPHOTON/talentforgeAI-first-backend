package com.laudado.talentforgeaibackend.security;

import com.laudado.talentforgeaibackend.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTWebService jwtToken;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public JWTFilter(
            JWTWebService jwtToken,
            MyUserDetailsService userDetailsService
    ) {
        this.jwtToken = jwtToken;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        /*
         * These endpoints do not require authentication.
         * Do not attempt to process a JWT for them.
         */
        String path = request.getServletPath();

        if (path.equals("/auth/register/jobseeker")
                || path.equals("/auth/register/recruiter")
                || path.equals("/auth/login")) {

            filterChain.doFilter(request, response);
            return;
        }

        /*
         * Get the Authorization header.
         */
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        /*
         * If there is a Bearer token, extract the username from it.
         */
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            try {
                username = jwtToken.getUsername(token);
            } catch (Exception e) {
                /*
                 * Invalid JWT.
                 *
                 * Do not authenticate the request.
                 * Let Spring Security decide whether the endpoint
                 * requires authentication.
                 */
                filterChain.doFilter(request, response);
                return;
            }
        }

        /*
         * Authenticate the user if:
         * 1. We successfully extracted a username from the JWT.
         * 2. There isn't already an authentication in the SecurityContext.
         */
        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            /*
             * Make sure the JWT actually belongs to this user
             * and is still valid.
             */
            if (jwtToken.tokenIsValid(token, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        /*
         * Continue to the next filter/controller.
         */
        filterChain.doFilter(request, response);
    }
}