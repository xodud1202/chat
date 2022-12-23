package com.xodud1202.chat.biz.controller;

import com.xodud1202.chat.support.controller.TycBaseController;
import com.xodud1202.chat.support.security.TycSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@RestController
public class TycIndexController extends TycBaseController {
	@GetMapping("/")
	public void mainForm(HttpServletResponse response) throws IOException {
		if(TycSession.isLogin()) {
			response.sendRedirect("/friend/list/form");
		}
		response.sendRedirect("/customer/login/form");
	}
}
