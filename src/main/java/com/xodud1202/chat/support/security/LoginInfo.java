package com.xodud1202.chat.support.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xodud1202.chat.biz.domain.Login;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Customer Login Domain
 * The @JsonSerialize annotation must be specified to store the session in Redis.
 * @author xodud1202
 * @since  2022.12.22
 */
@Data
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInfo implements UserDetails {
	Login user;

	public LoginInfo() {
		this.user = new Login();
	}

	public LoginInfo(Login user) {
		this.user = user;
	}

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 않았는지 리턴. (true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 마료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화(사용가능)인지 리턴 (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고있는 권한 목록은 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
}
