package com.teeya.authorization.config;

import com.teeya.authorization.service.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * AuthorizationServerConfig配置主要是覆写如下的三个方法，分别针对clients、endpoints、security配置。
 */
@Configuration
@EnableAuthorizationServer// 注解开启验证服务器 提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error等endpoints端点
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    /*@Autowired
    DataSource dataSource;*/

    //Authentication 管理者, 起到填充完整 Authentication的作用
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    /**
     * 配置客户端信息  clientId scope redirect_uri等  假如从数据库中读取，相对应的表为oauth_client_details
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalSecret = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        //配置两个客户端,一个用于password认证一个用于client认证  内存方式
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .redirectUris("http://www.baidu.com")
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret);
        //从数据库查客户端clientId等信息  只需要配置数据源以及建立对应的表  数据库方式
        //clients.jdbc(dataSource);
    }

    /**
     * 管理令牌  比如token保存等
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //tokenStore实现的三种保存方式（官网）：InMemoryTokenStore（内存）, JdbcTokenStore（数据库，许默认实现oauth_access_token以及oauth_refresh_token表结构）, JwtTokenStore（jwt的方式，既不保存到内存也不保存到数据库，而是将相关信息编码到token令牌里面）
        //源码中则还有另外一个 RedisTokenStore（redis的方式） 可查看TokenStore接口的实现类，其中有两个JwtTokenStore的实现类，这里就归结于一种吧
        //这里自定义redis的方式，使用jwt生成token，并保存到redis，方便token令牌的主动过期以及管理等
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))//需要配置redis
                .tokenEnhancer(tokenEnhancerChain()) //token增强器，通过自定义token可添加额外的信息  项目用的是JWT
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 定义令牌端点上的安全约束
     * @param oauthServer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //允许表单认证
        oauthServer
                // .tokenKeyAccess("isAuthenticated()")//开启/oauth/token_key验证端口需要权限访问  默认denyAll()
                //.tokenKeyAccess("permitAll()")//开启/oauth/token_key验证端口无权限访问  默认denyAll()
                //.checkTokenAccess("isAuthenticated()")//开启/oauth/check_token验证端口需权限访问  默认denyAll()
                .allowFormAuthenticationForClients();//允许表单认证
        //oauthServer.allowFormAuthenticationForClients();
    }


    /**
     * 这里tokenStore保存使用的是jwt的方式
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        //基于jwt实现令牌（Access Token）
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        String signingKey = "123456";//使用jwt需要设置的秘钥进行签名，即加密（生产环境需设置复杂点）  可考虑对称性加密  实际可用RSA非对称公私钥加密
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    /**
     * 通过使用token增强器可往token添加额外的信息 这里往jwt令牌添加自定义的信息
     * @return
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomJwtToken(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    @Bean
    //@Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //defaultTokenServices.setAccessTokenValiditySeconds(); refresh_token 的有效时长 (秒), 默认 30 天
        //defaultTokenServices.setRefreshTokenValiditySeconds(); access_token 的有效时长 (秒), 默认 12 小时
        //defaultTokenServices.setSupportRefreshToken(); refreshToken端点是否可复用 默认true，如果为 false, 每次请求刷新都会删除旧的 refresh_token, 创建新的 refresh_token
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());//token增强器  可往token添加额外的信息
        defaultTokenServices.setTokenStore(tokenStore());//token存储方式
        return defaultTokenServices;
    }

}

