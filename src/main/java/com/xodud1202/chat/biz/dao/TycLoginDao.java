package com.xodud1202.chat.biz.dao;

import com.xodud1202.chat.biz.domain.Login;
import com.xodud1202.chat.support.annotaion.xodudDs;
import org.springframework.stereotype.Repository;

@xodudDs
@Repository
public interface TycLoginDao {
	Login getLoginIdInfo(Login param);
}
