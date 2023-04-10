package com.xodud1202.chat.support.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xodud1202.chat.biz.service.TycLoginService;
import com.xodud1202.chat.support.security.LoginInfo;
import com.xodud1202.chat.support.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TycLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private TycLoginService loginService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		// SecurityContextHolder.getContext().setAuthentication(authentication);

		// 로그인 상세 정보
		LoginInfo loginDetails = (LoginInfo) authentication.getDetails();
		loginDetails = loginService.loadUserByUsername(loginDetails.getUsername());

		// 세션 생성
		this.createSession(request, loginDetails);

		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("returnUrl", "/");
		StringUtils.write(response, result);
	}

	/**
	 * Session 생성
	 * @param request - HttpServletRequest
	 * @param loginDetails - 로그인 상세 정보
	 */
	private void createSession(HttpServletRequest request, LoginInfo loginDetails) {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(1800);
		session.setAttribute("session", loginDetails);
	}
}
