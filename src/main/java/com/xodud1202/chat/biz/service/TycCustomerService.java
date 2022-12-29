package com.xodud1202.chat.biz.service;

import com.xodud1202.chat.biz.dao.TycCustomerDao;
import com.xodud1202.chat.biz.domain.Customer;
import com.xodud1202.chat.support.env.TycConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;


@Slf4j
@Service
@Transactional("xodudTxnManager")
public class TycCustomerService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;		// 비밀번호 암호화 Bean

	@Autowired
	TycCustomerDao customerDao;

	/**
	 * 고객 아이디 카운트 조회
	 * @author xodud1202
	 * @since 2022.12.28
	 */
	public int getCustomerIdCount(Customer param) {
		return customerDao.getCustomerIdCount(param);
	}

	/**
	 * 회원 가입 로직
	 * @author xodud1202
	 * @since 2022.12.29
	 */
	public int createCustomerInfo(Customer param) {
		// 회원가입 등급 브론즈
		param.setCustGrade(TycConstants.CustGrade.BRONZE.value());

		// 고객 비밀번호 암호화
		param.setPwd(bCryptPasswordEncoder.encode(param.getPwd()));

		// 고객 정보 등록
		int result = this.insertCust(param);
		if(result > 0) {
			param.setRegNo(param.getCustNo());
			param.setUdtNo(param.getCustNo());
			this.updateTbCustRegNo(param);
		}

		return result;
	}

	/**
	 * 회원 등록 번호 수정
	 * @author xodud1202
	 * @since 2022.12.29
	 */
	public void updateTbCustRegNo(Customer param) {
		customerDao.updateTbCustRegNo(param);
	}

	/**
	 * 회원 정보 등록 (TB_CUST)
	 * @author xodud1202
	 * @since 2022.12.29
	 */
	public int insertCust(Customer param) {
		return customerDao.insertCust(param);
	}
}
