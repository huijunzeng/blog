### 执行命令`mvn install common`安装基础工程
***
#### 工具控制台访问地址：
工具名称 | 访问地址 | 官方/学习文档 | 备注 
 :---:  | :---: | :---: | :---: 
查看es信息 | http://129.211.34.120:9200/ | https://www.elastic.co/cn/ | elasticSearch 
Kibana|http://129.211.34.120:5601/ | https://www.elastic.co/cn/ | 日志系统可视化 
Zipkin控制台 | http://129.211.34.120:9411/ | https://zipkin.io/ | 链路追踪可视化 
Nacos控制台 | http://129.211.34.120:8848/nacos | https://nacos.io/zh-cn/docs/what-is-nacos.html | 注册中心以及配置中心，持久化部署，需要执行sql脚本，路径在`\sql\nacos\nacos_config.sql`
Sentinel控制台(需启动jar包) | http://129.211.34.120:8088 | https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D | 熔断器 
RabbitMQ控制台 | http://129.211.34.120:15672 | https://www.rabbitmq.com/ | 消息队列 
xxl-job控制台| http://129.211.34.120:9090/xxl-job-admin/ | https://www.xuxueli.com/xxl-job/ | 分布式定时任务,需要执行sql脚本，路径在`\sql\xxljob\xxl_job.sql` 
Swagger接口文档 | http://129.211.34.120:9800/swagger-ui/index.html | http://springfox.github.io/springfox/ | springfox的SwaggerV3版本 
Portainer控制台 | http://129.211.34.120:9000 | https://github.com/portainer/portainer | 管理docker容器 

日志配置：elk + filebeat  本地启动输出日志到elk，需要下载安装filebeat，然后配置logstash的输出地址（`129.211.34.120:5044`）以及监听的日志文件路径(启动fielbeat看到输出 `Connection to backoff(async(tcp://129.211.34.120:5044)) established` 表示连接成功)


一个实体类在多个系统中唯一，在客户端创建VO接收来自服务端的对象

优点：
      如果更新的字段影响当前进程的业务，只需要更新客户端的VO及唯一的服务端实体类，如果更新的字段不影响当前进程的业务，则只需更新唯一的服务端实体类。且不影响分库

缺点：
      增加进程之间通讯的频率，增加团队之间协作的成本



构造Dockerfile时，需要自行更改docker服务器的ip地址并且开放docker的2375远程端口(具体步骤[以ubuntu系统为例子]：
1）停止docker服务 `sudo sysctl stop docker`
2）编辑docker启动配置文件`vim /lib/systemd/system/docker.service`，注释掉`ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock这一行，并新添加ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock`，退出保存配置文件 
3）重启docker服务 `sudo sysctl start docker`


分布式服务，id使用雪花算法（mp主键生成策略IdType.ASSIGN_ID），所以数据库主键用bigint，对应实体类用Long