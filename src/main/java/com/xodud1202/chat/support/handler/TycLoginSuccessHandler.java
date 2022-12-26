package com.xodud1202.chat.support.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class TycLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		response.sendRedirect("/");
	}

	public static void write(HttpServletResponse response, Object oResult) throws IOException {
		Writer writer = null;
		try {
			response.setContentType("text/xml; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write((new ObjectMapper()).writeValueAsString(oResult));
			writer.flush();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
