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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Log4j2
public class BasicAuthFilter extends OncePerRequestFilter implements AuthFilter {

	private final UserService userService;

	@Autowired
	public BasicAuthFilter(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		// Do not allow empty headers
		if (header == null || header.isEmpty() || !StringUtils.startsWith(header, "Basic ")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
			return;
		}

		User requestedUser = extractAndDecodeHeader(header, "Basic");
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
		return StringUtils.startsWithAny(request.getRequestURI(), "/users/register", "/users/login", "/users/checkUserId");
	}
}
