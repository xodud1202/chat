package com.xodud1202.chat.support.env;

/**
 * 변경될 소지가 있는 변수 값을 정의
 * @author xodud1202
 * @since  2022.12.22
 */
public class TycConstants {
	// 기본 패키지
	public static final String BASE_PACKAGE = "com.xodud1202.chat";

	// DOMAIN 패키지
	public static final String DOMAIN_PACKAGE = "com.xodud1202.chat.biz.domain";

	// 고객등급
	public enum CustGrade {
		  BRONZE("G110_10")		// 브론즈
		, SILVER("G110_20")		// 실버
		, GOLD("G110_30")		// 골드
		, VIP("G110_40")		// VIP
		, VVIP("G110_50")		// VVIP
		;

		private String value;

		private CustGrade(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}
}
