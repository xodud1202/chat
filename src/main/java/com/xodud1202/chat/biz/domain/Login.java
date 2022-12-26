package com.xodud1202.chat.biz.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xodud1202.chat.support.security.Password;
import com.xodud1202.chat.support.utils.CryptoUtils;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 고객 Domain
 * @JsonSerialize 애노테이션을 지정해야 세션을 레디스에 저장할 수 있다.
 * @author xodud1202
 * @since  2022.12.22
 */
@Data
@SuppressWarnings("serial")
public class Login {

	// 세션을 JSON 형식으로 레디스에 저장하려면 기본 생성자를 반드시 명시해야 한다
	public Login() {}

	private String custId;			// 고객ID
	private String custNm;			// 고객명
	private String username;
	private String password;
	private String pwd;				// 비밀번호
	private String custGrade;		// 고객등급
	private String cellPhone;		// 휴대전화번호
	private String email;			// 이메일
	private String sexType;			// 성별
	private String frontGb;			// 프론트구분(P:PC, M:모바일)
	private String custGradeNm;		// 고객등급명
	private String mailRecvYn;		// 마케팅 메일 수신 동의
	private String smsRecvYn;		// 마케팅 SMS 수신 동의
	private String ipAddr;			// 로그인 IP

	// 암호화 대상 복호화 처리 =================================================
	public String getCustNm() {
		this.custNm = CryptoUtils.decryptAES(this.custNm);
		return this.custNm;
	}

	public String getCellPhnno() {
		this.cellPhone = CryptoUtils.decryptAES(this.cellPhone);
		return this.cellPhone;
	}

	public String getEmail() {
		this.email = CryptoUtils.decryptAES(this.email);
		return this.email;
	}
	// 암호화 대상 복호화 처리 =================================================

	// 로그인 패스워드 암호화
	public String encryptSha512(String pwd) {
		return Password.encryptSha512(pwd);
	}
}
