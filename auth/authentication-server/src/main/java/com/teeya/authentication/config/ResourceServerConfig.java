package com.teeya.authentication.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    /**
     * 与资源安全配置相关
     * @param resources
     */
   /* @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }*/

    /**
     *  与http安全配置相关
     *  优先级的问题  WebSecurityConfigurerAdapter的configure优于这个
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        /*http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                //.antMatchers("/oauth/authorize").permitAll()
                .antMatchers("/order/**").authenticated();*///配置/order请求路径的访问控制，必须认证过后才可以访问
        // @formatter:on
       /* http.
                csrf().disable()
                .exceptionHandling()
                //.authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();*/
        // // 对 api/order 请求进行拦截   验证accessToken  与controller 的要有关系
               http.authorizeRequests().antMatchers("/test/**").authenticated();
    }

}
