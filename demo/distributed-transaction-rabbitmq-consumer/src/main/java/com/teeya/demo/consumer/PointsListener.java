package com.teeya.demo.consumer;

import com.rabbitmq.client.Channel;
import com.teeya.common.core.util.JSONUtils;
import com.teeya.demo.entity.form.PointSaveForm;
import com.teeya.demo.service.PointsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 积分队列消息消费者
 * @author ZJH
 * @since 2020-05-21
 */
@Component
@Slf4j
public class PointsListener {

    @Autowired
    private PointsService pointsService;

    /**
     * 监听积分队列，并做相对应逻辑处理
     * @param message 消息内容
     * @param channel 通道
     */
    @RabbitListener(queues = {"${base.config.rabbitmq.points-queue}"})
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "${base.config.rabbitmq.points-exchange}", durable = "true", type = "topic"), //交换机默认持久化
            value = @Queue(value = "${base.config.rabbitmq.points-queue}", durable = "true"),
            key = "${base.config.rabbitmq.points-route-key}"
    ))
    public void redirect(Message message, Channel channel) throws IOException {
        // 从队列中取出订单号
        byte[] body = message.getBody();
        String content = new String(body,"UTF-8");
        log.info("消费消息，消息内容为：{}", content);
        PointSaveForm pointSaveForm = new PointSaveForm();
        pointSaveForm.setAccountId(JSONUtils.jsonToTree(content,"id").asLong());
        pointSaveForm.setAccountName(JSONUtils.jsonToTree(content,"name").asText());
        pointSaveForm.setPoint(50);
        pointsService.save(pointSaveForm);
        // 确认消息有没有被收到，false表示手动确认  在处理完消息时，返回应答状态
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
