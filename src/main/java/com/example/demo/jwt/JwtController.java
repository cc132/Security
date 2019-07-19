package com.example.demo.jwt;

import java.util.Arrays;
import java.util.List;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
public class JwtController {

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/jwt/login")
    public String login(Long userId) {
        // 1. 验证用户名和密码
        // 2. 验证成功生成token
        
        String token = jwtToken.generateToken(userId);
        return token;
    }

    @GetMapping("/jwt/getUserInfo")
    public String getUserInfo(@RequestHeader("Authorization") String authHeader) throws AuthenticationException {
        // 黑名单token
        List<String> blacklistToken = Arrays.asList("禁止访问的token");
        Claims claims = jwtToken.getClaimByToken(authHeader);
        if (claims == null || JwtToken.isTokenExpired(claims.getExpiration()) || blacklistToken.contains(authHeader)) {
            throw new AuthenticationException("token 不可用");
        }

        String userId = claims.getSubject();
        // 根据用户id获取接口数据返回接口
        return userId;
    }
}
