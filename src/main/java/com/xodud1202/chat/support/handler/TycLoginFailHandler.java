package com.xodud1202.chat.support.handler;

import com.mysql.cj.exceptions.PasswordExpiredException;
import com.xodud1202.chat.support.utils.MapUtils;
import com.xodud1202.chat.support.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TycLoginFailHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		// Map<String, Object> paramsMap = MapUtils.getParameterMap(request);
		Map<String, Object> result = new HashMap<>();
		result.put("returnUrl", "/");
		if (exception instanceof UsernameNotFoundException) { // 로그인 시에 비밀번호 5회 이상 틀려서 계정이 잠기는 경우
			result.put("status", "NOT_CUST_ID");
		} else if (exception instanceof BadCredentialsException) { // 휴면회원 로그인 시
			result.put("status", "NOT_MATCH_PWD");
		} else {
			result.put("status", "ETC_ERROR");
		}

		StringUtils.write(response, result);
	}
}
