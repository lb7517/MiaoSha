package com.lb.miaosha.component;

import com.lb.miaosha.dto.Order;
import com.lb.miaosha.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreateOrderSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    //注意此处如果是实体类要现实序列化
    public void sendMessage(Order order){
        LOGGER.info("开始发送消息");
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER.getExchange(),
                QueueEnum.QUEUE_ORDER.getRouteKey(), order);
        LOGGER.info("消息发送结束");
    }

}
