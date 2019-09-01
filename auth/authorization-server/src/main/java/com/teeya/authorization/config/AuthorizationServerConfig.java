package com.teeya.authorization.config;

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

import java.util.Arrays;

/**
 * AuthorizationServerConfig配置主要是覆写如下的三个方法，分别针对clients、endpoints、security配置。
 */
@Configuration
@EnableAuthorizationServer// 注解开启验证服务器 提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error等endpoints端点
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomeUserDetailsService userDetailsService;

    // 可在properties文件配置信息  这里采用redis默认信息
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 内存存储  实际应用需采用跟数据库
     * 客户端相关配置 我们的认证服务器会给哪些第三方发令牌
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置两个客户端认证,一个用于password认证一个用于client认证
        //clients.jdbc(dataSource)  //@Autowired  DataSource dataSource;  配置客户端信息，从数据库中读取，对应oauth_client_details表
        // OAuth 2.0定义了五种授权方式 client_credentials,authorization_code,password,implicit,refresh_token分别对应客户端模式、授权码模式、密码模式、隐式授权模式   如果使用refresh_token端点，那么需要把refresh_token写入授权类型
        // 生成的token有两种  一个是access_token，另一种是refresh_token，前者的有效期默认是12小时，后者有效期则比较长，在refresh_token未过期的情况下，可以使用后者重新获取新的access_token。refresh token有两种使用模式：重复使用和非重复使用（顾名思义，不做解释），前者会延用初次生成token的过期时间，后者则会重新返回新的默认或者设置的过期时间  jti：jwt token id  token的唯一属性
        clients.inMemory().withClient("client_1")
                .secret("123456")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "implicit", "refresh_token")//定义授权方式  只有使用匹配的类型才能获取token，比如这里不能使用password授权模式
                .scopes("select")//相当于权限，配置了这个参数，请求里面可以不带scope参，如果带了参数，必须在配置的这个scope范围之内
                .authorities("client")
                .redirectUris("http://www.baidu.com")
                .accessTokenValiditySeconds(100)//access_token过期时间
                .refreshTokenValiditySeconds(100)//fresh_token过期时间
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret("123456")
                .redirectUris("http://www.baidu.com")
                .accessTokenValiditySeconds(100)//access_token过期时间
                .refreshTokenValiditySeconds(100);//fresh_token过期时间
    }

    /**
     *  配置AuthorizationServerEndpointsConfigurer众多相关类，包括配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory)) //token保存到redis
                .tokenEnhancer(tokenEnhancerChain()) //自定义token  项目用的是JWT
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)// 不能重复使用refresh_token，每次使用会重新生成一个新的refresh_token返回  默认是可以重复使用
                .userDetailsService(userDetailsService);//访问refresh_token授权模式时，需要配置userDetailsService，否则报错java.lang.IllegalStateException: UserDetailsService is required.
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
               // .tokenKeyAccess("isAuthenticated()")//开启/oauth/token_key验证端口需要权限访问  默认denyAll()
                //.tokenKeyAccess("permitAll()")//开启/oauth/token_key验证端口无权限访问  默认denyAll()
                //.checkTokenAccess("isAuthenticated()")//开启/oauth/check_token验证端口需权限访问  默认denyAll()
                .allowFormAuthenticationForClients();//允许表单认证
    }


    /**
     * 自定义token
     *
     * @return tokenEnhancerChain
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new customJwtToken(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    /**
     * jwt token的生成配置
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        String signingKey = "123456";//秘钥  对称性加密  实际可用RSA非对称公私钥加密
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        return defaultTokenServices;
    }

}

