package com.example.demo.d1.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private ObjectMapper objectMapper;
	
	public CustomAuthenticationFailureHandler() {
		this.setDefaultFailureUrl("/failure");
	}
	@Override
	    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	        /**
	         * 如果配置ConfigConstant.LOGIN_RESPONSE_TYPE="JSON"，则返回JSON，否则使用页面跳转
	         */
	        if("JSON".equalsIgnoreCase("json1")) {
	            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            response.setContentType("application/json;charset=UTF-8");
	            response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
	        } else {
	        	//System.out.println("===页面跳转");
	            super.onAuthenticationFailure(request, response, exception);
	        }
	    }
}
