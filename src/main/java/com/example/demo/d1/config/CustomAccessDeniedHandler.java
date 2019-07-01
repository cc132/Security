package com.example.demo.d1.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.demo.d1.entity.ControllerResult;

import net.sf.json.JSONObject;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ControllerResult forbidden = new ControllerResult();
        forbidden.setRefuseReason("权限不够1111");
        response.getWriter().print(JSONObject.fromObject(forbidden).toString());
	}

}
