package com.teeya.gateway.routes;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.teeya.common.core.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * todo
 * Nacos动态路由  继承DiscoveryClientRouteDefinitionLocator父类，重写加载路由配置信息方法
 *
 * 路由配置可以简单分为两大类： 代码配置（硬编码写死的方式，拓展性差；假如有改动，需要重新编译代码打包上传） 以及 非代码配置（包括读取配置文件/数据源【包括 MySQL 等关系型数据库以及 redis 等缓存型数据库】/配置中心等外部的方式）
 *
 * 所以要实现动态路由，我们需要了解 非代码配置 的加载过程
 * 源码关键类： org.springframework.cloud.gateway.route.RouteDefinitionLocator 路由定义定位器的顶级接口，读取路由配置信息
 * 从基于RouteDefinitionLocator接口的实现类中：
 *      1.CachingRouteDefinitionLocator --RouteDefinitionLocator包装类， 缓存目标RouteDefinitionLocator 为routeDefinitions提供缓存功能
 *      2.CompositeRouteDefinitionLocator --RouteDefinitionLocator包装类，组合多种 RouteDefinitionLocator 的实现，为 routeDefinitions提供统一入口
 *      3.PropertiesRouteDefinitionLocator --从配置文件(yml / properties，匹配spring.cloud.gateway前缀的配置项) 读取RouteDefinition
 *      4.DiscoveryClientRouteDefinitionLocator --从注册中心( 例如，Nacos / Eureka / Consul / Zookeeper / Etcd 等 )读取RouteDefinition
 *      5.RouteDefinitionRepository --从存储介质( 例如，内存 / Redis / MySQL 等 )读取RouteDefinition  所以假如需要拓展Redis或者MySQL实现动态路由，可继承此类重写加载路由方法
 *      ***默认实现的InMemoryRouteDefinitionRepository内存读取属于RouteDefinitionRepository的一种
 * 可把实现路由定义的方式归纳为三种：
 *      1.配置文件
 *      2.注册中心（结合配置中心）
 *      3.存储介质
 *
 * @Author: ZJH
 * @Date: 2020/3/3 11:35
 */

@Component
@Slf4j
public class NacosRouteDefinitionRepository /*extends DiscoveryClientRouteDefinitionLocator*/ {

    private static final String DATA_ID = "gateway-routes";
    private static final String GROUP_ID = "blog";

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;


    private ConfigService configService;

    /*public NacosRouteDefinitionRepository(ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
        super(discoveryClient, properties);
    }*/

    @PostConstruct
    public void init() {
        log.info("gateway route init...");
        try{
            configService = initConfigService();
            if(configService == null){
                log.warn("initConfigService fail");
                return;
            }
            String configInfo = configService.getConfig(DATA_ID, GROUP_ID, 3000);
            log.info("获取网关当前配置:\r\n{}",configInfo);
            List<RouteDefinition> definitionList = JSONUtils.jsonToList(configInfo, RouteDefinition.class);
            for(RouteDefinition definition : definitionList){
                log.info("update route : {}",definition.toString());
                dynamicRouteService.add(definition);
            }
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误",e);
        }
        dynamicRouteByNacosListener(DATA_ID, GROUP_ID);
    }

    /**
     * 监听Nacos下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}",configInfo);
                    List<RouteDefinition> definitionList = JSONUtils.jsonToList(configInfo, RouteDefinition.class);
                    for(RouteDefinition definition : definitionList){
                        log.info("update route : {}",definition.toString());
                        dynamicRouteService.update(definition);
                    }
                }
                @Override
                public Executor getExecutor() {
                    log.info("getExecutor\n\r");
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("从nacos接收动态路由配置出错!!!",e);
        }
    }

    /**
     * 初始化网关路由 nacos config
     * @return
     */
    private ConfigService initConfigService(){
        try{
            Properties properties = new Properties();
            properties.setProperty("serverAddr","129.211.34.120:8848");
            properties.setProperty("namespace","a4d0cf40-1125-4263-81e5-3f628bc90302");
            return configService= NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误",e);
            return null;
        }
    }

    /*@Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    private ConfigService configService;

    public NacosRouteDefinitionRepository(ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
        super(discoveryClient, properties);
        addListener();
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            // 参数分别对应Nacos配置中心的 dataId、 group 以及 timeoutMs 超时时间(毫秒)
            String content = configService.getConfig("gateway-routes", GROUP_ID,3000);
            log.info("routes from config center : {}", content);
            List<RouteDefinition> routeDefinitions = getListByStr(content);
            return Flux.fromIterable(routeDefinitions);
        } catch (NacosException e) {
            log.error("getRouteDefinitions by nacos error", e);
        }
        return Flux.fromIterable(Collections.EMPTY_SET);
    }

    *//**
     * 添加Nacos监听
     *//*
    private void addListener() {
        try {
            configService.addListener(DATA_ID, GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        } catch (NacosException e) {
            log.error("nacos-addListener-error", e);
        }
    }

    private List<RouteDefinition> getListByStr(String content) {
        if (!StringUtils.isEmpty(content)) {
            return JSONUtils.jsonToList(content, RouteDefinition.class);
        }
        return new ArrayList<>(0);
    }*/
}
