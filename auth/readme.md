### Oauth2.0认证授权模块，结合Spring Security实现系统的权限控制

***

划分两部分：authorization-server认证服务 和 authentication-server授权服务

authorization-server认证服务（认证服务器）:处理与token相关的业务；提供/oauth/authorize,/oauth/token,/oauth/check_token,/oauth/confirm_access,/oauth/error等endpoints端点api  

authentication-server授权服务（资源服务器）：处理资源权限业务；判断用户是否拥有访问该资源的权限