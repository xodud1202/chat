package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.biz.service.TycCustomerService;
import com.xodud1202.chat.support.controller.TycBaseController;
import com.xodud1202.chat.support.security.TycSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class TycIndexController extends TycBaseController {
	@Autowired
	private TycCustomerService customerService;

	@GetMapping("/")
	public void mainForm(HttpServletResponse response) throws IOException {
		log.info("Session check ::: {}", TycSession.isLogin());
		log.info("Session check ::: {}", TycSession.getInfo());
		if(TycSession.isLogin()) {
			response.sendRedirect("/friend/list/form");
			return;
		}

		response.sendRedirect("/customer/login/form");
	}
}
