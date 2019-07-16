package com.lb.miaosha.config;

import com.lb.miaosha.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 * */
@Configuration
public class RabbitMqConfig {

    @Bean
    DirectExchange orderDirect(){
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER.getExchange())
                .durable(true)
                .build();
    }

    @Bean
    Queue orderQueue(){
        //durable是持久化
//        return QueueBuilder.durable(QueueEnum.QUEUE_ORDER.getName()).build();
        return new Queue(QueueEnum.QUEUE_ORDER.getName());
    }

    /**
     * 队列绑定到交换机上
     * */
    @Bean
    Binding orderBinding(DirectExchange directExchange, Queue queue){
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(QueueEnum.QUEUE_ORDER.getRouteKey());
    }

}
