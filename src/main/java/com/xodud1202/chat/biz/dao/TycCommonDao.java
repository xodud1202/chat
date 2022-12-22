package com.xodud1202.chat.biz.dao;

import com.xodud1202.chat.biz.domain.CommonCode;
import com.xodud1202.chat.support.annotaion.xodudDs;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@xodudDs
@Repository
public interface TycCommonDao {
	/**
	 * 공통코드 리스트 조회
	 * @author xodud1202
	 * @since 2022.02.18
	 */
	Collection<CommonCode> getCommonCodeList(CommonCode param);

	/**
	 * 마지막 등록 PK(auto_increament) 값 조회
	 * @author xodud1202
	 * @since 2022.11.03
	 */
	int getLastInsertId();
}
