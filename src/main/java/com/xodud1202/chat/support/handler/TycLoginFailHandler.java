package com.xodud1202.chat.support.handler;

import com.xodud1202.chat.support.utils.MapUtils;
import com.xodud1202.chat.support.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TycLoginFailHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		// Parameter
		Map<String, Object> paramsMap = MapUtils.getParameterMap(request);
		Map<String, Object> result = new HashMap<>();

		// 로그인 실패 로직 추가 (어떤 Exception인지 확인 후 결과 값 세팅 필요) - Exception도 세팅 필요.

		// 로그인 실패 정보 세팅 (return data)
//		result.put("result", object)

		StringUtils.write(response, result);
	}
}
