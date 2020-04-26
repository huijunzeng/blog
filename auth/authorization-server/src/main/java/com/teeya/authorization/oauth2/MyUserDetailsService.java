package com.teeya.authorization.oauth2;


import com.teeya.authorization.entity.RoleEntity;
import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查出UserDetails并比较
     * 根据提供的密码加密器passwordEncoder（默认是）去比较当前请求的密码与数据库查出来的密码比较，不匹配则报错：
     * {
     *     "error": "invalid_grant",
     *     "error_description": "Bad credentials"
     * }
     * 可通过在这根据用户名从数据库查找该用户名数据，并且封装该用户的角色
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 内存的方式
        /*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
        return (UserDetails) manager;
        */
        //查询账号是否存在，是就返回一个UserDetails的对象，若不存在就抛出异常！
       /* Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
        authoritiesSet.add(new SimpleGrantedAuthority("USER"));// 授权权限
        return new User(username, new BCryptPasswordEncoder().encode("1234567"), true, true, true, true,authoritiesSet);*/
        // 数据库的方式
        // 从数据库验证用户密码 查询用户权限  测试账号 用户名：admin  密码：password
        UserEntity userEntity = authorizationService.getByUniqueId(username);
        log.info(userEntity.toString());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        /*grantedAuthorities = userVo.getRoleIds().stream().map(e -> new SimpleGrantedAuthority(e.trim())).collect(Collectors.toSet());*/
        //grantedAuthorities.add(new SimpleGrantedAuthority("USER"));// 授权权限
        /*if (userEntity != null) {
            List<TbPermission> tbPermissions = tbPermissionMapper.selectByUserId(tbUser.getId());

            tbPermissions.stream().forEach(tbPermission -> {
                if (tbPermission != null && tbPermission.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }*/
        // （1）假如WebSecurityConfig中的AuthenticationManagerBuilder配置了passwordEncoder，但在数据库中保存的密码不是明文的而是已经用相同的passwordEncoder加密后的密文，那么封装查询出来的用户User的密码时就不需要再用passwordEncoder加密
        // （2）假如WebSecurityConfig中的AuthenticationManagerBuilder配置了passwordEncoder，但在数据库中保存的密码是明文，那么封装查询出来的用户User的密码时就需要再用相同的passwordEncoder加密
        //return new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), true, true, true, true, grantedAuthorities);
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, this.obtainGrantedAuthorities(userEntity));

    }

    /**
     * 获得用户的所有角色的权限集合.
     * @param userEntity
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(UserEntity userEntity) {
        List<RoleEntity> roles = authorizationService.queryListByUsername(userEntity.getUsername());
        log.info("user:{},roles:{}", userEntity.getUsername(), roles);
        if (null == roles || roles.isEmpty()) {
            return null;
        }
        // 用户的所有角色code
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
    }

}
