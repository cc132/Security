package com.example.demo.d1.globalexceptionhandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String getAccessDeniedExceptionResponse1(Exception e) {
		System.out.println("======exception handler");
		System.out.println("=======" + e.getClass().getName());
		return e.getMessage() + "222";
	}
}
