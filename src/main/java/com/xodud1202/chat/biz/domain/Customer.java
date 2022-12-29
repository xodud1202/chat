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
	private String custNm;		// 고객명
	private String email;		// 이메일
	private String pwd;			// 비밀번호
	private String sexType;		// 성별
	private String cellPhone;	// 휴대폰번호
	private String mailRecvYn;	// 메일 수신 여부
	private String smsRecvYn;	// 문자 수신 여부
	private String custGrade;	// 고객등급
	private String custGradeUdtReason;	// 고객등급변경사유
}
