package com.teeya.authorization.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * UserDetails => Spring Security基础接口，包含某个用户的账号，密码，权限，状态（是否锁定）等信息。只有getter方法。
 * Authentication => 认证对象，认证开始时创建，认证成功后存储于SecurityContext上下文
 * principal => 用户信息对象，是一个Object，通常可转为UserDetails
 *
 * 继承UserDetailsService，实现返回一个用户信息的UserDetails对象  SpringSecurity框架
 * @Author: ZJH
 * @Date: 2019/6/27 13:17
 */

@Component
public class CustomeUserDetailsService implements UserDetailsService {

    /**
     * 查出UserDetails并比较
     * 可通过在这根据用户名从数据库查找该用户名数据
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        /*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
*/
        //查询账号是否存在，是就返回一个UserDetails的对象，若不存在就抛出异常！
        Set<GrantedAuthority> AuthoritiesSet = new HashSet<GrantedAuthority>();
        AuthoritiesSet.add(new SimpleGrantedAuthority("USER"));// 授权权限
        return new User("user_1",new BCryptPasswordEncoder().encode("123456"), true, true, true, true,AuthoritiesSet);
        //return (UserDetails) manager;
    }

}
