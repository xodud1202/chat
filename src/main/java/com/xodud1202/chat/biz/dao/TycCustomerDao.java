package com.xodud1202.chat.biz.dao;

import com.xodud1202.chat.biz.domain.Customer;
import com.xodud1202.chat.support.annotaion.xodudDs;
import org.springframework.stereotype.Repository;

@xodudDs
@Repository
public interface TycCustomerDao {
	int getCustomerIdCount(Customer param);
}
