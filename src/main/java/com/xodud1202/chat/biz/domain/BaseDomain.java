package com.xodud1202.chat.biz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Domain의 기본 Super class
 * Domain 클래스 생성 시 이 클래스를 상속받아서 처리한다.
 * @author xodud1202
 * @since  2022.12.22
 */
@Data
@SuppressWarnings("serial")
public class BaseDomain {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer regNo;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String regNm;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String regDt;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String udtId;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String udtNm;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String udtDt;
}
