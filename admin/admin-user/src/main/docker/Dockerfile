#指定基础镜像，在其上进行定制
#openJDK太大，自己构建Oracle的jdk1.8镜像（可根据实际情况自行选择jdk镜像）
FROM java:alpine

#维护者信息
MAINTAINER zjh <18826233829@163.com>

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的jar包到容器里  使用COPY命令亦可
ADD admin-user-1.0-SNAPSHOT.jar admin-user-1.0-SNAPSHOT.jar

#bash方式执行，使jar可访问
#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
#RUN bash -c "touch /demo-1.0.0.jar"

#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 9803

#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
#服务器寒碜 8g内存，所有工具以及服务都在同一样机器，所以只能JVM调优，设置内存参数了
#-Xmx400m 设置JVM最大内存为400M  -Xms400m 设置JVM最小内存为400M(与最大值保持一致可以防止每次垃圾回收(GC)后JVM对内存进行再分配)
#-Xmn150m 设置新生代大小,新生代大小会影响到老年代的大小,Sun推荐设置为总内存的3/8  -Xss1024k 每个线程堆栈大小,值越小能生成的线程数越多,但是一般不超过5000个,在递归场景下值太小可能会造成堆栈溢出
ENTRYPOINT ["java","-Xmx256m","-Xms128m","-Xmn150m","-Xss1024k","-jar","admin-user-1.0-SNAPSHOT.jar"]