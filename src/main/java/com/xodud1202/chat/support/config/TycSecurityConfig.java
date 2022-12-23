package com.xodud1202.chat.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Java Security를 이용한 Login 처리
 *
 * @author xodud1202
 * @since  2022.12.23
 */
@Configuration
public class TycSecurityConfig extends WebSecurityConfigurerAdapter {
	// 정적인 파일에 대한 요청들
    private static final String[] AUTH_WHITELIST = {
            "/image/**", "/ux/**"
    };

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // login 없이 접근 허용 하는 url
                .antMatchers("/", "/customer/**").permitAll()
                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated();
    }

	@Override
    public void configure(WebSecurity web) throws Exception {
        // 정적인 파일 요청에 대해 무시
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
