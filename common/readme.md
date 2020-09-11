### common模块
***
子模块common-core包含一些基础代码，如异常类、响应封装类  
子模块common-web包含一些框架公共配置类以及公共实体类（引入了spring-boot-starter-web，所以不能在gateway模块[底层用的是webflux]引入，以及不涉及数据库查询的模块，
因此假如有涉及到全模块都需要引入的情况，那么该写在common-core子模块内）