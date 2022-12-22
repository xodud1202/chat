package com.xodud1202.chat.biz.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 공통코드  Domain
 * @author xodud1202
 * @since  2022.12.22
 */
@SuppressWarnings("serial")
@Data
public class CommonCode {
	private String cdGb;
	private String cd;
	private String cdNm;
	private String cdDesc;
	private int dispOrd;
	private String useYn;

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private String[] exceptCds;
}
