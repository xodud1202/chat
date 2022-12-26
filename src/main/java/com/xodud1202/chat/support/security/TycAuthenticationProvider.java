package com.xodud1202.chat.support.security;

import com.xodud1202.chat.biz.service.TycLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
@RequiredArgsConstructor
public class TycAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private TycLoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)  authentication;
		String custId = token.getName();
		String pwd = (String) token.getCredentials();

		LoginInfo user = (LoginInfo) loginService.loadUserByUsername(custId);
		if(user == null) {
			log.info("가입된 정보가 없는 계정입니다.");

		}

		return new UsernamePasswordAuthenticationToken(user, pwd, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
