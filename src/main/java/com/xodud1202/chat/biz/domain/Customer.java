package com.xodud1202.chat.biz.domain;

import lombok.Data;

/**
 * 친구목록 Domain
 * @author xodud1202
 * @since  2022.12.23
 */
@Data
@SuppressWarnings("serial")
public class Customer extends BaseDomain {
	private Integer custNo;		// 고객번호

	private int count;			// 카운트 수량

	private String custId;		// 고객 계정
}
