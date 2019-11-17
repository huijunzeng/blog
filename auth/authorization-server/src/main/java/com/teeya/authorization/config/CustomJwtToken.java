package com.teeya.authorization.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义JWT token  可拓展
 * @Author: ZJH
 * @Date: 2019/6/26 18:50
 */
public class CustomJwtToken implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        //自定义token内容
        Map<String, Object> additionalInfo = new HashMap();
        additionalInfo.put("organization", oAuth2Authentication.getName());//加入organization组织机构信息  这里加入用户名
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
