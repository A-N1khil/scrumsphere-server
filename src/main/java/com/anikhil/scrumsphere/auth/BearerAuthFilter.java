package com.anikhil.scrumsphere.auth;

import com.anikhil.scrumsphere.users.models.User;
import com.anikhil.scrumsphere.users.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Component
@Log4j2
@Profile("prod")
public class BearerAuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Autowired
    public BearerAuthFilter(UserService userService) {
        this.userService = userService;
    }

    private User extractAndDecodeHeader(String header) {

        // Remove the "Basic " prefix
        String base64Credentials = StringUtils.removeStart(header, "Bearer ");

        // Decode the Base64 encoded credentials
        byte[] decoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decoded);

        // Split the credentials into username and password
        String[] splitCreds = StringUtils.split(credentials, ":");
        return new User(splitCreds[0], splitCreds[1]);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOGGER.info("Extracting and decoding the header");
        LOGGER.debug("Debug Extracting and decoding the header");

        String header = request.getHeader("Authorization");

        // Do not allow empty headers
        if (header == null || header.isEmpty() || !StringUtils.startsWith(header, "Basic ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
            return;
        }

        User requestedUser = extractAndDecodeHeader(header);
        User userFromDb = this.userService.findUserByUserId(requestedUser.getUserId());

        // Check if the user exists
        if (userFromDb == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
            return;
        }

        boolean isAuthenticated = userFromDb.getPassword().equals(requestedUser.getPassword());

        if (!isAuthenticated) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            return;
        }

        // Set the user to the Security Context
        UserDetails authUser = org.springframework.security.core.userdetails.User.builder()
                .username(userFromDb.getUserId())
                .password(userFromDb.getPassword())
                .roles("USER")
                .build();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return StringUtils.startsWith(request.getRequestURI(), "/users/create");
    }
}
