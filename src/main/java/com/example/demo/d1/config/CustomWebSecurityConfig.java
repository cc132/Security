package com.example.demo.d1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private CustomUsernamePasswordAuthenticationConfig customUsernamePasswordAuthenticationConfig;

    /**
     * 用户认证配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 指定用户认证时，默认从哪里获取认证用户信息
         */
        auth.userDetailsService(userDetailsServiceImpl);
    }

    /**
     *	 静态资源、特殊路径进行放行
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/css/**","/failure.html","/failure");
    }
    /**
     * Http安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        http
            .csrf()
                .disable()
            .apply(customUsernamePasswordAuthenticationConfig)
//            .and()
//            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                //.antMatchers("/user/**").hasAuthority("USER")
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies("remember-me-cookie-name")
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .key("unique-and-secret")
                    .rememberMeCookieName("remember-me-cookie-name")
                    .tokenRepository(getPersistentTokenRepository())
                    .tokenValiditySeconds(1 * 60)
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
//                .and()
//               .addFilterBefore(new RewriteAccessDenyFilter(), FilterSecurityInterceptor.class);
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         */
        return new BCryptPasswordEncoder();
    }

    /**
     * 指定保存用户登录“记住我”功能的Token的方法：
     * 默认是使用InMemoryTokenRepositoryImpl将Token放在内存中，
     * 如果使用JdbcTokenRepositoryImpl，会创建persistent_logins数据库表，并将Token保存到该表中。
     */
    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /**
         * 系统启动时自动创建表，只需要在第一次启动系统时创建即可，因此这行代码只在需要创建表时才启用
         */
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
