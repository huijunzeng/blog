package com.teeya.authorization.oauth2.granter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建自定义的token授权模式  关键是继承AbstractTokenGranter抽象类以及重写抽象类的getOAuth2Authentication方法
 * OAuth2Authentication 存放用户的认证信息
 * @Author: ZJH
 * @Date: 2019/12/23 16:16
 */
public abstract class CustomTokenGranter extends AbstractTokenGranter {
    private boolean allowRefresh;

    protected CustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.allowRefresh = false;
    }

    /*public void setAllowRefresh(boolean allowRefresh) {
        this.allowRefresh = allowRefresh;
    }*/

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        OAuth2AccessToken token = super.grant(grantType, tokenRequest);
        if (token != null) {
            DefaultOAuth2AccessToken norefresh = new DefaultOAuth2AccessToken(token);
            if (!this.allowRefresh) {
                norefresh.setRefreshToken(null);
            }

            token = norefresh;
        }

        return token;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
        UserDetails details = getUserDetails(parameters);
        if (details == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Authentication authentication = super.getOAuth2Authentication(client, tokenRequest);
        authentication.setDetails(details);
        authentication.setAuthenticated(true);
        return authentication;
    }

    protected abstract UserDetails getUserDetails(Map<String, String> parameters);
}
