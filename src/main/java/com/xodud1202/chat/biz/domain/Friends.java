package com.xodud1202.chat.biz.domain;

import lombok.Data;

/**
 * 친구목록 Domain
 * @author xodud1202
 * @since  2022.12.23
 */
@Data
@SuppressWarnings("serial")
public class Friends extends BaseDomain {
	private Integer custFriendsIdx;		// 고객 친구 목록 일련번호

	private String custId;				// 고객 아이디
	private String friendId;			// 친구 아이디
	private String setName;				// 저장이름
	private String delYn;				// 삭제여부
}
