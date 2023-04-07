package com.xodud1202.chat.support.security;

import com.xodud1202.chat.biz.service.TycLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TycAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private TycLoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();		// 비밀번호 암호화 Bean
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)  authentication;
		String custId = token.getName();
		String pwd = (String) token.getCredentials();
		LoginInfo user = loginService.loadUserByUsername(custId);

		if(user == null) {
			throw new IllegalStateException("ID가 존재하지 않습니다.");
		} else if (!bCryptPasswordEncoder.matches(pwd, user.getPassword())) {
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		// return new UsernamePasswordAuthenticationToken(user.getUser().getCustNo(), pwd, authorities);
		return new UsernamePasswordAuthenticationToken(custId, pwd, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
