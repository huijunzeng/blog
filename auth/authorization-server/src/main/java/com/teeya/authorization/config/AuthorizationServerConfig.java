package com.teeya.authorization.config;

import com.teeya.authorization.config.jwt.CustomJwtToken;
import com.teeya.authorization.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
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

    @Autowired
    DataSource dataSource;
    // 自定义的用户管理类 用于从数据库中查找用户数据
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Authentication 管理者, 起到填充完整 Authentication的作用  从spring security中的WebSecurityConfigurerAdapter类注入
    @Autowired
    private AuthenticationManager authenticationManager;
    /*@Autowired
    AuthorizationCodeServices authorizationCodeServices;*/
    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    /**
     * OAuth 2.0定义了五种授权方式：
     *
     * 授权码模式（authorization code）（假设  微信第三方登录， 同意授权登录时会发送请求获取服务提供商（相当于微信）的code，然后携带code拿到token，然后就可以访问资源了）
     * 简化模式（implicit）
     * 密码模式（resource owner password credentials）
     * 客户端模式（client credentials）
     * 扩展模式（Extension）
     * ————————————————
     *
     * 配置客户端信息  clientId scope redirect_uri等  假如从数据库中读取，相对应的表为oauth_client_details
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 授权码模式：http://localhost:9777/oauth/authorize?client_id=test_client&response_type=code&redirect_uri=http://www.baidu.com  浏览器输入然后输入CustomeUserDetailsService类中写死的user以及password

        // 根据获取到的code请求获取token  http://localhost:9777/oauth/token?grant_type=authorization_code&code=QzQAV9&client_id=test_client&client_secret=test_secret&redirect_uri=http://www.baidu.com

        // password 方案一：明文存储，用于测试，不能用于生产
        // String finalSecret = "123456";
        // password 方案二：用 BCrypt 对密码编码
        // String finalSecret = new BCryptPasswordEncoder().encode("test_secret");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        // String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("test_secret");
        // 配置两个客户端,一个用于password认证一个用于client认证  内存方式
       /* clients.inMemory().withClient("test_client")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token")
                .scopes("select")
                .authorities("USER")
                .secret(finalSecret)
                .redirectUris("http://www.baidu.com")
                .and().withClient("test_client")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("USER")
                .secret(finalSecret);*/
        // 从数据库查客户端clientId等信息  只需要配置数据源以及建立对应的表oauth_client_details  数据库方式
        // 测试数据，数据库对应的client_id为test_client，client_secret为test_secret（数据库中显示的是BCrypt加密后的密文）
        // spring security5.0推荐使用BCrypt加密规则，不配置默认也是采用这个（也可通过这设置其他的加密规则）
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    /**
     * 管理令牌、授权码等等的配置（最重要的配置）  比如token保存等
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置authorizationCode/token的数据源、自定义的tokenServices等信息,配置身份认证器，配置认证方式，TokenStore，TokenGranter，允许请求方法类型
        endpoints
                .userDetailsService(myUserDetailsService)
                .authorizationCodeServices(authorizationCodeServices()) // 配置authorizationCode授权码的保存方式
                .tokenEnhancer(tokenEnhancerChain()) // token增强器，通过自定义token可添加额外的信息  项目用的是JWT
                .tokenServices(tokenServices()) // 配置自定义的tokenServices 比如这里的jwt + redis的格式保存
                .authenticationManager(authenticationManager) // 配置身份认证器
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST); // 允许请求方法类型
    }

    /**
     * 定义令牌端点上的安全约束  配置访问的一些权限设置
     * /oauth/authorize：授权端点。(授权码模式)
     * /oauth/token：令牌端点。（密码模式）
     * /oauth/confirm_access：用户确认授权提交端点。
     * /oauth/error：授权服务错误信息端点。
     * /oauth/check_token：用于资源服务访问的令牌解析端点。(检验token，get请求发送token参数)
     * /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()") // 开启/oauth/token_key验证端口无权限访问  默认denyAll()拒绝访问   permitAll()允许访问  isAuthenticated()认证访问
                .checkTokenAccess("permitAll()")
                // 是否允许表单认证，会调用ClientCredentialsTokenEndpointFilter判断是否需要拦截
                // 默认不配置的情况下，请求必须Basic Base64(client_id+client_secret)，即假如是postman测试的时候，需要在Authorization属性选择Basic，然后在Username以及Password的表单中相对应填写client_id和client_secret的值才能成功请求到token
                // 开启后，则可以在路径后直接拼接client_id和client_secret参数就能请求到token，当然上面的请求格式也一样支持，相对来说上面的那一种会安全点，所以我们大多取默认就行
                .allowFormAuthenticationForClients();
    }

    /**
     * AuthorizationCodeServices实现的两种保存方式（看AuthorizationCodeServices接口源码，有以下三个实现类）
     * （1）InMemoryAuthorizationCodeServices 内存的方式（默认配置是这个，可看AuthorizationServerEndpointsConfigurer类的源码）
     * （2）JdbcAuthorizationCodeServices 数据库的方式，源码实现crud，但必须要实现oauth_code表结构
     * （3）RandomValueAuthorizationCodeServices 父类，帮我们随机生成一个验证码，上面两者都继承了这个类
     * 授权码模式持久化授权码code
     *
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        // 授权码存储等处理方式类，使用jdbc，操作oauth_code表
        return new JdbcAuthorizationCodeServices(dataSource);
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

    /**
     * jwt  token的生成配置
     * @return jwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        String signingKey = "123456";// 使用jwt需要设置的秘钥进行签名，即加密（生产环境需设置复杂点）  可考虑对称性加密  实际可用RSA非对称公私钥加密
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 通过使用token增强器可往token添加额外的信息 这里往jwt令牌添加自定义的信息
     * @return tokenEnhancerChain
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomJwtToken(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    /**
     * 用作crud  对访问 Token 的格式和存储作进一步的封装
     * @return defaultTokenServices
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //defaultTokenServices.setSupportRefreshToken(true); // 是否支持refreshToken
        //defaultTokenServices.setSupportRefreshToken(false); // refreshToken端点是否可复用 默认true，如果为 false, 每次请求刷新都会删除旧的 refresh_token, 创建新的 refresh_token
        //defaultTokenServices.setAccessTokenValiditySeconds(); // refresh_token 的有效时长 (秒), 默认 30 天
        //defaultTokenServices.setRefreshTokenValiditySeconds(); // access_token 的有效时长 (秒), 默认 12 小时
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());// token增强器  可往token添加额外的信息
        defaultTokenServices.setTokenStore(tokenStore());// token存储方式
        return defaultTokenServices;
    }

}

