package com.xodud1202.chat.support.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TycSession {
	private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

	public static boolean isLogin() {
		HttpSession session = getHttpServletRequest().getSession(true);
		return StringUtils.isNotBlank((String) session.getAttribute("custId"));
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
