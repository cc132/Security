package com.example.demo.d1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("customUsernamePasswordAuthenticationProvider")
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /**
         * 将未认证的Authentication转换成自定义的用户认证Token
         */
        CustomUsernamePasswordAuthenticationToken authenticationToken = (CustomUsernamePasswordAuthenticationToken) authentication;

        /**
         * 根据用户Token中的用户名查找用户信息，如果有该用户信息，则验证用户密码是否正确
         */
        UserDetails user = userDetailsServiceImpl.loadUserByUsername((String)(authenticationToken.getPrincipal()));
        if(user == null) {
            throw new InternalAuthenticationServiceException("CustomUsernamePasswordAuthenticationProvider获取认证用户信息失败");
        } else if(!this.passwordEncoder.matches((CharSequence) authenticationToken.getCredentials(), user.getPassword())) {
            throw new BadCredentialsException("用户名或密码不正确");
        }
        /**
         * 认证成功则创建一个已认证的用户认证Token
         */
        CustomUsernamePasswordAuthenticationToken authenticationResult = new CustomUsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        /**
         * 设置一些详情信息
         */
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        /**
         * 指定该认证处理器能对CustomUsernamePasswordAuthenticationToken对象进行认证
         */
        return CustomUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
