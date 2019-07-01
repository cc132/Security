package com.example.demo.d1.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.GenericFilterBean;

import com.example.demo.d1.entity.ControllerResult;

import net.sf.json.JSONObject;

public class RewriteAccessDenyFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            ControllerResult forbidden = new ControllerResult();
            forbidden.setRefuseReason("权限不够");
            response.getWriter().print(JSONObject.fromObject(forbidden).toString());
        }
    }
}
