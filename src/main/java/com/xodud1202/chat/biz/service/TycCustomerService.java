package com.xodud1202.chat.biz.service;

import com.xodud1202.chat.biz.dao.TycCustomerDao;
import com.xodud1202.chat.biz.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class TycCustomerService {
	@Autowired
	TycCustomerDao customerDao;

	/**
	 * 게시글 노출 관련 정보 UPDATE
	 * @author xodud1202
	 * @since 2022. 01. 05
	 */
	@Transactional("xodudTxnManager")
	public int getCustomerIdCount(Customer param) {
		return customerDao.getCustomerIdCount(param);
	}
}
