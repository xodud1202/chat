package com.xodud1202.chat.biz.service;

import com.xodud1202.chat.biz.dao.TycLoginDao;
import com.xodud1202.chat.biz.domain.Login;
import com.xodud1202.chat.support.security.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TycLoginService implements UserDetailsService {
	@Autowired
	private TycLoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Login param = new Login();
		param.setCustId(username);
		Login loginInfo = loginDao.getLoginIdInfo(param);
		return new LoginInfo(loginInfo);
	}
}
