package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.support.controller.TycBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/friend")
public class TycFriendController extends TycBaseController {
	@GetMapping("/list/form")
	public ModelAndView friendListForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("friends/friendsListForm");
		return mav;
	}

	@GetMapping("/search")
	public ModelAndView customerSearchForm() {
		ModelAndView mav = new ModelAndView();
		// mav.setViewName("friends/friendsListForm");
		return mav;
	}
}
