先mvn install common工程
工具访问地址：
查看es信息：http://129.211.34.120:9200/
访问es控制台：http://129.211.34.120:9100/
访问kibana：http://129.211.34.120:5601/
访问skywalking控制台：http://129.211.34.120:8080/
访问nacos控制台：http://129.211.34.120:8848/nacos
访问sentinel控制台：http://129.211.34.120:8088 （需启动jar包）
访问rabbitmq控制台：http://129.211.34.120:15671
访问xxl-job控制台：http://129.211.34.120:9090/xxl-job-admin/
访问swaggerApi: http://129.211.34.120:9800/swagger-ui.html
访问portainer控制台: http://129.211.34.120:9000

日志配置：elk + filebeat  本地启动输出日志到elk，需要下载安装filebeat，然后配置logstash的输出地址（129.211.34.120:5044）以及监听的日志文件路径(启动fielbeat看到输出 Connection to backoff(async(tcp://129.211.34.120:5044)) established 表示连接成功)


一个实体类在多个系统中唯一，在客户端创建VO接收来自服务端的对象

优点：
      如果更新的字段影响当前进程的业务，只需要更新客户端的VO及唯一的服务端实体类，如果更新的字段不影响当前进程的业务，则只需更新唯一的服务端实体类。且不影响分库

缺点：
      增加进程之间通讯的频率，增加团队之间协作的成本



构造Dockerfile时，需要自行更改docker服务器的ip地址并且开放docker的2375远程端口(具体步骤[以ubuntu系统为例子]：
1）停止docker服务 sudo sysctl stop docker
2）编辑docker启动配置文件vim /lib/systemd/system/docker.service，注释掉ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock这一行，并新添加ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock，退出保存配置文件 
3）重启docker服务 sudo sysctl start docker


分布式服务，id使用雪花算法（mp主键生成策略IdType.ASSIGN_ID），所以数据库主键用bigint，对应实体类用Long