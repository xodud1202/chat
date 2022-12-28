package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.biz.domain.Customer;
import com.xodud1202.chat.biz.service.TycCustomerService;
import com.xodud1202.chat.support.controller.TycBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/customer")
public class TycCustomerController extends TycBaseController {
	@Autowired
	TycCustomerService customerService;

	/**
	 * 로그인 화면
	 * @author xodud1202
	 * @since 2022.12.28
	 */
	@GetMapping("/login/form")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/loginForm");
		return mav;
	}

	/**
	 * 회원가입 화면
	 * @author xodud1202
	 * @since 2022.12.28
	 */
	@GetMapping("/join")
	public ModelAndView joinForm() {
		ModelAndView mav = new ModelAndView();
		//이용약관
		mav.addObject("policy1", "<h1>이용약관 입니다 텍스트트</h1>");
		//개인정보 처리방침
		mav.addObject("policy2", "<h2>개인정보 처리 방침 영역 입니다아아아 텍스트트<h2>");
		//마케팅 활용동의
		mav.addObject("policy3", "<h3 style='color:red;'>마케팅은 활용할게 없어요 하하하</h3>");

		mav.setViewName("customer/joinForm");
		return mav;
	}

	/**
	 * 고객 아이디 수 체크
	 * @author xodud1202
	 * @since  2022.12.28
	 */
	@GetMapping("/id/count")
	public int getCustomerIdCount(Customer param) {
		return customerService.getCustomerIdCount(param);
	}

	/**
	 * 고객 정보 저장 (회원가입)
	 * @author xodud1202
	 * @since 2022.12.28
	 */
	@PostMapping("/info")
	public Map<String, Object> createCustomerInfo(@RequestBody Customer param) {
		Map<String, Object> result = new HashMap<>();
		// TODO xodud1202 회원가입 로직 추가 필요
		result.put("isJoin", true);

		return result;
	}
}
