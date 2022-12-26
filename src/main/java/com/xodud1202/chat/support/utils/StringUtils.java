package com.xodud1202.chat.support.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class StringUtils {
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
