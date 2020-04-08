package com.teeya.authorization.oauth2.granter;

import com.teeya.authorization.oauth2.SmsCodeUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 创建自定义的token授权模式  继承自定义的CustomTokenGranter抽象类
 * @Author: ZJH
 * @Date: 2019/12/23 16:16
 */
public class PhoneCustomTokenGranter extends CustomTokenGranter {

    SmsCodeUserDetailsService smsCodeUserDetailsService;

    private static final String grantType = "phone_sms_code";// 自定义名称 在AuthorizationServerConfig配置类的authorizedGrantTypes属性值中引入（数据库对应字段authorized_grant_types）
    private boolean allowRefresh;

    public PhoneCustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, SmsCodeUserDetailsService smsCodeUserDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.smsCodeUserDetailsService = smsCodeUserDetailsService;
    }

    public void setAllowRefresh(boolean allowRefresh) {
        this.allowRefresh = allowRefresh;
    }

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        OAuth2AccessToken token = super.grant(grantType, tokenRequest);
        if (token != null) {
            DefaultOAuth2AccessToken norefresh = new DefaultOAuth2AccessToken((OAuth2AccessToken)token);
            if (!this.allowRefresh) {
                norefresh.setRefreshToken((OAuth2RefreshToken)null);
            }

            token = norefresh;
        }

        return (OAuth2AccessToken)token;
    }

    @Override
    protected UserDetails getUserDetails(Map<String, String> parameters) {
        String phone = parameters.get("phone");
        String smsCode = parameters.get("smsCode");
        return smsCodeUserDetailsService.loadUserByPhone(phone, smsCode);
    }
}
