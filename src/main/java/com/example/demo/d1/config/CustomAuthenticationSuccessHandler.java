package com.example.demo.d1.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 自定义的用户认证成功处理器
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    @Autowired
    private ObjectMapper objectMapper;

    public CustomAuthenticationSuccessHandler() {
        /**
         * 指定默认登录成功请求的URL和是否总是使用默认登录成功请求的URL
         * 注意：自定义的认证成功处理器，如果不指定，默认登录成功请求的URL是"/"
         */
        this.setDefaultTargetUrl("/user");
        this.setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        /**
         * 如果配置ConfigConstant.LOGIN_RESPONSE_TYPE="JSON"，则返回JSON，否则使用页面跳转
         */
        if("JSON".equalsIgnoreCase("json1")) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
