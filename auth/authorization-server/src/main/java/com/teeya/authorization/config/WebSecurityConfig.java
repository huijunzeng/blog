package com.teeya.authorization.config;

import com.teeya.authorization.service.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomeUserDetailsService userDetailsService;

    /**
     * 认证对象管理
     * springboot2.1.x版本需要注入authenticationManager  不然AuthorizationServerConfig授权服务器依赖AuthenticationManager报错
     * Field authenticationManager in com.example.oauth.config.AuthorizationServerConfig required a bean of type 'org.springframework.security.authentication.AuthenticationManager' that could not be found.
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 认证对象管理构造器
     * new BCryptPasswordEncoder().encode("123456")
     * Spring security 5.0中新增了多种加密方式，也改变了密码的格式，所以内存密码时需要用以上方式
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user_1").password(new BCryptPasswordEncoder().encode("123456")).authorities("USER")
                .and()
                .withUser("user_2").password(new BCryptPasswordEncoder().encode("123456")).authorities("USER");*/
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     *  HTTP请求安全处理  对请求信息的设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
       /* http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()//authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/oauth/authorize").authenticated()
                .antMatchers("/oauth/*").authenticated();*/
        // @formatter:on

        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll(); //新增login form支持用户登录及授权
    }

}
