package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.support.controller.TycBaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TycIndexController extends TycBaseController {
	@GetMapping({"/", "/friends/list/form"})
	public ModelAndView mainForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("friends/friendsListForm");
		return mav;
	}
}
