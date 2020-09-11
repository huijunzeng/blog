package com.teeya.authorization.config;

import com.teeya.authorization.oauth2.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security配置
 *
 * WebSecurityConfigurerAdapter与ResourceServerConfigurerAdapter的区别：
 *     WebSecurityConfigurerAdapter默认情况下是spring security的http配置
 *     ResourceServerConfigurerAdapter默认情况下是spring security oauth2的http配置
 * 优先级的问题  WebSecurityConfigurerAdapter的HttpSecurity低于这个ResourceServerConfigurerAdapter（优先级高的会覆盖优先级低的）
 * 查看源码，ResourceServerConfigurerAdapter的order为3，WebSecurityConfigurerAdapter的order为100，order值越小，优先级越高，所以默认情况下ResourceServerConfigurerAdapter执行生效
 * 假如需要优先执行WebSecurityConfigurerAdapter的HttpSecurity，在WebSecurityConfig配置类添加注解@Order(-1)，数值只要小于3即可
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * 认证对象管理
     * springboot2.1.x版本需要注入父类的authenticationManager  不然AuthorizationServerConfig授权服务器依赖AuthenticationManager报错
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
     * 密码加密规则 不可逆  默认是用BCrypt加密方式
     * Spring security 5.0中新增了多种加密方式，也改变了密码的格式，所以存密码时需要用以上方式
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证对象管理构造器
     * userDetailsService 自定义的用户管理类
     * @return
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user_1").password("123456").authorities("USER")
                .and()
                .withUser("user_2").password(new BCryptPasswordEncoder().encode("123456")).authorities("USER");*/
        // spring security5.0推荐使用BCrypt加密规则，不配置默认也是采用这个（也可通过这设置其他的加密规则）
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     *  HTTP请求安全处理  对请求信息的设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            //允许对于网站静态资源以及某些请求路径的无授权访问
            .antMatchers("/oauth/**", "/actuator/**","/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/webjars/springfox-swagger-ui/images/**","/swagger-resources/configuration/*","/swagger-resources","/v3/api-docs").permitAll()
            //设置拦截规则
            .anyRequest().authenticated()
            .and()
            .formLogin().and()
            .csrf().disable()
            .httpBasic();
    }

    /**
     * Web层面的配置，一般用来配置无需权限校验的路径，也可以在HttpSecurity中配置，但是在web.ignoring()中配置效率更高。
     * web.ignoring()是一个忽略的过滤器，而HttpSecurity中定义了一个过滤器链，即使permitAll()放行还是会走所有的过滤器，
     * 直到最后一个过滤器FilterSecurityInterceptor认定是可以放行的，才能访问。
     */
    @Override
    public void configure(WebSecurity web) {
        /*super.configure(web);*/
        web.ignoring().antMatchers("/favor.ioc");
    }
}
