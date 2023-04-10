package com.xodud1202.chat.support.security;

import com.xodud1202.chat.biz.domain.Login;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TycSession {
	/**
	 * Get Session Info.
	 *
	 * @return 세션 정보
	 */
	public static Login getInfo() {
		LoginInfo loginDetails = (LoginInfo)RequestContextHolder.currentRequestAttributes().getAttribute("session", RequestAttributes.SCOPE_SESSION);

		if (loginDetails == null) {
			return null;
		}

		return loginDetails.getUser();
	}

	private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

	/*public static boolean isLogin() {
		HttpSession session = getHttpServletRequest().getSession(true);
		return StringUtils.isNotBlank((String) session.getAttribute("custId"));
	}*/

	/**
	 * 로그인 여부
	 *
	 * @return true/false
	 */
	public static boolean isLogin() {
		LoginInfo loginDetails = (LoginInfo) RequestContextHolder.currentRequestAttributes().getAttribute("session", RequestAttributes.SCOPE_SESSION);

		if (loginDetails == null) {
			return false;
		}

		return loginDetails.isLogin();
	}

	public static String getAttribute(String key) {
		HttpSession session = getHttpServletRequest().getSession(true);

		if (session != null && session.getAttribute(key) != null) {
			return (String)session.getAttribute(key);
		}

		return "";
	}

	public static void setAttribute(String key, String value) {
		getHttpServletRequest().getSession(true).setAttribute(key, value);
	}

	/**
	 * 세션 값 삭제
	 * @param key - 명칭
	 */
	public static void removeAtrribute(String key) {
		HttpSession session = getHttpServletRequest().getSession();
		if (session != null && session.getAttribute(key) != null) {
			session.removeAttribute(key);
		}
	}
}
