package com.xodud1202.chat.support.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<?> e = request.getParameterNames();

        while(e.hasMoreElements()) {
            String key = (String)e.nextElement();
            map.put(key, request.getParameter(key));
        }

        return map;
    }
}
