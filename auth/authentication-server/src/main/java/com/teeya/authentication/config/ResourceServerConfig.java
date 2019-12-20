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
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    /**
     *  与http安全配置相关 可配置拦截什么URL、设置什么权限等安全控制
     *  优先级的问题  WebSecurityConfigurerAdapter的configure优于这个
     *  这里配置了拦截规则，可以省掉像authorization-server服务需要写WebSecurityConfig类去配置
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
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/test/**", "/oauth2_token/**").permitAll()
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
                .resourceId("blog")// 假如数据库oauth_client_details表的resource_ids资源ID集合不为空，那么这里需要配上有相对应的值
                .tokenStore(tokenStore());// 配置token的验证  与授权服务的token保存方式保持一致，才能实现token的验证
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
