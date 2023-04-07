package com.xodud1202.chat.support.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xodud1202.chat.biz.domain.Login;
import com.xodud1202.chat.support.security.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TycAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public TycAuthenticationFilter () {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
		// fetch를 통해 application/json 형태로 데이터를 받았을 경우
		LoginInfo loginRequest = new LoginInfo(objectMapper.readValue(request.getInputStream(), Login.class));
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
			loginRequest.getUsername(), loginRequest.getPassword());

		/* submit으로 /login 요청을 보냈을 경우
		// Get the username and password from the request parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password); */

		authRequest.setDetails(loginRequest);		// Set the LoginInfo object as the details of the authRequest
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // You can customize the response for a successful authentication here.
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        // You can customize the response for an unsuccessful authentication here.
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
