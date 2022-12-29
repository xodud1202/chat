package com.xodud1202.chat.biz.dao;

import com.xodud1202.chat.biz.domain.Customer;
import com.xodud1202.chat.support.annotaion.xodudDs;
import org.springframework.stereotype.Repository;

@xodudDs
@Repository
public interface TycCustomerDao {
	/**
	 * 고객 아이디 카운트 조회
	 */
	int getCustomerIdCount(Customer param);

	/**
	 * 회원 정보 등록 (TB_CUST)
	 */
	int insertCust(Customer param);

	void updateTbCustRegNo(Customer param);
}
