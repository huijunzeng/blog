package com.teeya.authorization.config.jwt;

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

        /**
         * 返回格式：access_token - 访问令牌，用于资源访问
         * refresh_token - 当访问令牌失效，使用这个令牌重新获取访问令牌
         * token_type - 令牌类型，这里是Bearer也就是基本HTTP认证
         * expire_in - 过期时间
         * organization - 自定义的内容
         * jti - JWT ID
         * ————————————————
         * {
         *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyXzEiLCJzY29wZSI6WyJzZWxlY3QiXSwib3JnYW5pemF0aW9uIjoidXNlcl8xIiwiZXhwIjoxNTc0MTk0MTU0LCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6IjY1OGVhOTY4LTk1YzQtNGNmYi1iOWQ4LTk3YmY3NzVhNzEzMyIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.LumICKkLtCEPu0nR4kI7GdknBY66B2CuhHpjMbbOUoE",
         *     "token_type": "bearer",
         *     "expires_in": 43198,
         *     "scope": "select",
         *     "organization": "user_1",
         *     "jti": "658ea968-95c4-4cfb-b9d8-97bf775a7133"
         * }
         */


        //自定义token内容
        Map<String, Object> additionalInfo = new HashMap();
        additionalInfo.put("organization", oAuth2Authentication.getName());//加入organization组织机构信息  这里加入用户名
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
