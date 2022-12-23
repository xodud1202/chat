package com.xodud1202.chat.support.controller;

import com.xodud1202.chat.biz.domain.Login;
import com.xodud1202.chat.support.security.TycSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller Advice
 * @author xodud1202
 * @since  2022.12.22
 */
@ControllerAdvice
@Slf4j
public class TycBaseController {
	@Autowired
	private Environment env;

	/**
	 * Json data 형식 외의 모든 문자열의 앞, 뒤 공백 제거
	 * Json data 형식의 모든 문자열의 앞, 뒤 공백 제거 시는 AdmStringTrim에서 처리 (@RequestBody annotation 이용 시)
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
//		stringtrimmer.getAsText().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ModelAttribute("env")
	public Environment getEnvironment() {
		return env;
	}

	/**
	 * Get 세션 정보
	 * 1.View 단에서 사용법
	 * 		th:value="${sessionInfo.userId}"
	 * 2.Java 단에서 사용법
	 * 		super.getSession().getUserId()
	 * @return
	 */
	@ModelAttribute("sessionInfo")
	public Login getSession() {
		Login info = new Login();
		info.setCustId(TycSession.getAttribute("custId"));
		info.setCustNm(TycSession.getAttribute("custNm"));
		return info;
	}
}
