package com.teeya.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.teeya.user.utils.HttpClientUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * github第三方登录
 * @Author: ZJH
 * @Date: 2019/12/6 14:29
 */

@RestController
public class GitController {

    private static String GITHUB_CLIENT_ID = "b7d7abb5ae6d517b207d";
    private static String GITHUB_CLIENT_SECRET = "4b2e1335aabb1023ea0a5b43345fcaa592260e67";
    private static String GITHUB_REDIRECT_URL = "https://www.baidu.com";

    @RequestMapping("/githubLogin")
    public void githubLogin(HttpServletResponse response) throws Exception {
        // Github认证服务器地址
        String url = "https://github.com/login/oauth/authorize";
        // 生成并保存state，忽略该参数有可能导致CSRF攻击
        //String state = oauthService.genState();
        // 传递参数response_type、client_id、state、redirect_uri
        //String param = "response_type=code&" + "client_id=" + GITHUB_CLIENT_ID + "&state=" + state
        //       + "&redirect_uri=" + GITHUB_REDIRECT_URL;
        String param = "response_type=code&" + "client_id=" + GITHUB_CLIENT_ID + "&redirect_uri=" + GITHUB_REDIRECT_URL;

        // 1、请求Github认证服务器
        response.sendRedirect(url + "?" + param);
    }

    @RequestMapping("/githubCallback")
    public void githubCallback(String code, String state, HttpServletResponse response) throws Exception {
        // 验证state，如果不一致，可能被CSRF攻击
        /*if(!oauthService.checkState(state)) {
            throw new Exception("State验证失败");
        }*/

        // 2、向GitHub认证服务器申请令牌
        String url = "https://github.com/login/oauth/access_token";
        // 传递参数grant_type、code、redirect_uri、client_id
        String param = "grant_type=authorization_code&code=" + code + "&redirect_uri=" +
                GITHUB_REDIRECT_URL + "&client_id=" + GITHUB_CLIENT_ID + "&client_secret=" + GITHUB_CLIENT_SECRET;

        // 申请令牌，注意此处为post请求
        String result = HttpClientUtils.sengRequest(url, param, "POST");

        /*
         * result示例：
         * 失败：error=incorrect_client_credentials&error_description=The+client_id+and%2For+client_secret+passed+are+incorrect.&
         * error_uri=https%3A%2F%2Fdeveloper.github.com%2Fapps%2Fmanaging-oauth-apps%2Ftroubleshooting-oauth-app-access-token-request-errors%2F%23incorrect-client-credentials
         * 成功：access_token=7c76186067e20d6309654c2bcc1545e41bac9c61&scope=&token_type=bearer
         */
        Map<String, String> resultMap = (Map<String, String>) JSONObject.parseArray(result, Map.class);
        // 如果返回的map中包含error，表示失败，错误原因存储在error_description
        if(resultMap.containsKey("error")) {
            throw new Exception(resultMap.get("error_description"));
        }

        // 如果返回结果中包含access_token，表示成功
        if(!resultMap.containsKey("access_token")) {
            throw new Exception("获取token失败");
        }

        // 得到token和token_type
        String accessToken = resultMap.get("access_token");
        String tokenType = resultMap.get("token_type");
        System.out.println("access_token: " + accessToken  + "=============" + "token_type: " + tokenType);
        // 3、向资源服务器请求用户信息，携带access_token和tokenType
        String userUrl = "https://api.github.com/user";
        String userParam = "access_token=" + accessToken + "&token_type=" + tokenType;

        // 申请资源
        String userResult = HttpClientUtils.sengRequest(userUrl, userParam, "POST");

        // 4、输出用户信息
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(userResult);
    }
}
