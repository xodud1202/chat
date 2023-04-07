package com.xodud1202.chat.support.config;

import com.xodud1202.chat.biz.service.TycLoginService;
import com.xodud1202.chat.support.filter.TycAuthenticationFilter;
import com.xodud1202.chat.support.handler.TycLoginSuccessHandler;
import com.xodud1202.chat.support.security.TycAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Java Security를 이용한 Login 처리
 *
 * @author xodud1202
 * @since  2022.12.23
 */
@Configuration
@EnableWebSecurity //시큐리티 필터가 등록
public class TycSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private TycAuthenticationProvider tycAuthenticationProvider;

	// 정적인 파일에 대한 요청들
    private static final String[] AUTH_WHITELIST = {
            "/images/**", "/ux/**"
    };

	public TycSecurityConfig(TycAuthenticationProvider tycAuthenticationProvider) {
		this.tycAuthenticationProvider = tycAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// login 없이 접근 허용 하는 url
				.antMatchers("/", "/login", "/signin", "/customer/**").permitAll()
				// 그 외 모든 요청은 인증과정 필요
				.anyRequest().authenticated()
		.and()
				.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/customer/login/form")
				.successForwardUrl("/")
		.and().logout()
		.and().addFilterBefore(tycAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder encodePWD(){ //비밀번호 암호화를 위해 사용 시큐리티는 비밀번호가 암호화 되있어야 사용가능하다
		return new BCryptPasswordEncoder();   //회원가입할때 쓰면된다.
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new TycLoginService();
	}

	// 시큐리티가 대신 로그인해주는데 password를 가로채는데
	// 해당 password가 뭘로 해쉬화해서 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교가능
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(encodePWD());
	}*/
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tycAuthenticationProvider);
    }

	@Override
    public void configure(WebSecurity web) throws Exception {
        // 정적인 파일 요청에 대해 무시
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

	@Bean
	public TycAuthenticationFilter tycAuthenticationFilter() throws Exception {
		TycAuthenticationFilter authenticationFilter = new TycAuthenticationFilter();
		authenticationFilter.setFilterProcessesUrl("/login");
		authenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		authenticationFilter.afterPropertiesSet();
		return authenticationFilter;
	}

	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public TycLoginSuccessHandler loginSuccessHandler() {
		return new TycLoginSuccessHandler();
	}
}
