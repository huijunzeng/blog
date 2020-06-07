package com.teeya.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Bean方式是启动时自动创建队列，不需要手动在rabbitmq控制台创建
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    /**
     * 积分处理队列
     */
    @Value("${base.config.rabbitmq.points-queue}")
    public String POINTS_QUEUE_NAME = "points.queue";
    /**
     * 积分重试队列
     */
    @Value("${base.config.rabbitmq.points-retry-queue}")
    public String POINTS_RETRY_QUEUE_NAME = "points.retry.queue";
    /**
     * 积分处理路由KEY
     */
    @Value("${base.config.rabbitmq.points-route-key}")
    public String POINTS_ROUTE_NAME = "points.route.key";
    /**
     * 积分处理交换机
     */
    @Value("${base.config.rabbitmq.points-exchange}")
    public String POINTS_EXCHANGE_NAME = "points.exchange";

    /**
     * 积分队列
     * @return
     */
    @Bean
    public Queue pointsQueue() {
        // durable持久化  防止mq服务器宕机造成数据丢失
        // return QueueBuilder.durable("POINTS_QUEUE_NAME").build();  这是另一种写法
        return new Queue(POINTS_QUEUE_NAME, true);
    }

    /**
     * 补单队列
     * @return
     */
    @Bean
    public Queue pointsRetryQueue() {
        // return QueueBuilder.durable("POINTS_RETRY_QUEUE_NAME").build();  这是另一种写法
        return new Queue(POINTS_RETRY_QUEUE_NAME, true);
    }

    /**
     * 积分处理交换机（Direct Exchange（直连交换机 一对一））
     * @return
     */
    @Bean
    public DirectExchange pointsExchange() {
        // Direct Exchange（直连交换机 一对一）
        // ExchangeBuilder.directExchange(POINTS_EXCHANGE_NAME);  这是另一种写法
        return new DirectExchange(POINTS_EXCHANGE_NAME);
    }

    /**
     * 交换机绑定到积分队列
     * @return
     */
    @Bean
    public Binding bindingPointsQueueToExchange() {
        // return new Binding(POINTS_QUEUE_NAME, Binding.DestinationType.QUEUE, POINTS_EXCHANGE_NAME, POINTS_ROUTE_NAME, null);  这是另一种写法
        return BindingBuilder.bind(pointsQueue()).to(pointsExchange()).with(POINTS_ROUTE_NAME);
    }

    /**
     * 交换机绑定到补单队列
     * @return
     */
    @Bean
    public Binding bindingPointsRetryQueueToExchange() {
        // return new Binding(POINTS_RETRY_QUEUE_NAME, Binding.DestinationType.QUEUE, POINTS_EXCHANGE_NAME, POINTS_ROUTE_NAME, null);  这是另一种写法
        return BindingBuilder.bind(pointsRetryQueue()).to(pointsExchange()).with(POINTS_ROUTE_NAME);
    }
}
