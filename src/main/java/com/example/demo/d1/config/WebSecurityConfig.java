package com.example.demo.d1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private DbUserDetailsService dbUserDetailsService;
	@Autowired
	private DataSource dataSource;
    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * -------------------------------------------
     * loginPage("/login") 会检测请求方式为get或者post的/login
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .failureUrl("/login?error")
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies("remember-me-cookie-name")
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .key("unique-and-secret")
                    .rememberMeCookieName("remember-me-cookie-name")
                    .tokenRepository(getPersistentTokenRepository())
                    .tokenValiditySeconds(24 * 60 * 60);
    }
    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(dbUserDetailsService).passwordEncoder(passwordEncoder());
    }
    
    //对静态资源放行
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/css/**");
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
    	JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    	jdbcTokenRepository.setDataSource(dataSource);
    	//jdbcTokenRepository.setCreateTableOnStartup(true);
    	return jdbcTokenRepository;
    }
}
