package com.xodud1202.chat.support.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TycAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public TycAuthenticationFilter (AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		log.info("check login_info ::: {} / {}", request.getParameter("username"), request.getParameter("password"));
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"));
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
