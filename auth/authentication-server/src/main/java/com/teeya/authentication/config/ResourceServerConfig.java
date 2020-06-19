package com.teeya.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
// 启用SpringSecurity过滤器，过滤器通过传入的OAuth2令牌token对请求进行身份验证
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "blog";

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomAuthExceptionHandler customAuthExceptionHandler;

    /**
     *  与http安全配置相关 可配置拦截什么URL、设置什么权限等安全控制
     *  WebSecurityConfigurerAdapter与ResourceServerConfigurerAdapter的区别：
     *     WebSecurityConfigurerAdapter默认情况下是spring security的http配置
     *     ResourceServerConfigurerAdapter默认情况下是spring security oauth2的http配置
     *  优先级的问题  WebSecurityConfigurerAdapter的HttpSecurity低于这个（优先级高的会覆盖优先级低的）
     *  查看源码，ResourceServerConfigurerAdapter的order为3，WebSecurityConfigurerAdapter的order为100，order值越小，优先级越高，所以默认情况下ResourceServerConfigurerAdapter执行生效
     *  假如需要优先执行WebSecurityConfigurerAdapter的HttpSecurity，在WebSecurityConfig配置类添加注解@Order(-1)，数值只要小于3即可
     *  这里配置了拦截规则，可以省掉像authorization-server服务需要写WebSecurityConfig类去配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 配置对某些访问路径放行(不需要携带token即可访问)
        http.authorizeRequests()
                .antMatchers("/test/**", "/actuator/**", "/auth/hello").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 与资源安全配置相关
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                // 假如数据库oauth_client_details表的resource_ids资源ID集合不为空，那么这里需要配上有相对应的值
                .resourceId(DEMO_RESOURCE_ID)
                // 配置token的验证  与授权服务的token保存方式保持一致，才能实现token的验证
                .tokenStore(tokenStore())
                .accessDeniedHandler(customAuthExceptionHandler)
                .authenticationEntryPoint(customAuthExceptionHandler);
    }

    /**
     * tokenStore实现的四种保存方式（看TokenStore接口源码，有以下五个实现类）：
     * （1）InMemoryTokenStore 内存的方式
     * （2）JdbcTokenStore 数据库的方式，源码实现crud，但必须要实现oauth_access_token以及oauth_refresh_token表结构
     * （3）JwtTokenStore  jwt的方式，既不保存到内存也不保存到数据库，而是将相关信息编码到token令牌里面 ； 其中有两个JwtTokenStore的实现类，可以相当于是一种
     * （4）源码中还有另外一个 RedisTokenStore（redis的方式）  key键源码中设定了，token保存在access键下
     * （默认不配置的情况下，假如有JwtAccessTokenConverter的JwtTokenStore，则用JwtTokenStore的方式；否则用InMemoryTokenStore内存的方式    详细可看AuthorizationServerEndpointsConfigurer类的源码）
     *  这里自定义redis的方式，使用jwt生成token，并保存到redis，方便token令牌的主动过期以及管理等  通过tokenServices去整合封装
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        // 基于jwt实现令牌（Access Token）
        // return new JwtTokenStore(accessTokenConverter());
        return new RedisTokenStore(redisConnectionFactory);
    }


}
