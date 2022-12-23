package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.support.controller.TycBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/customer")
public class TycCustomerController extends TycBaseController {
	@GetMapping("/login/form")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/loginForm");
		return mav;
	}
}
