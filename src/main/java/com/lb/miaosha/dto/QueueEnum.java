package com.lb.miaosha.dto;

/**
 * 消息队列枚举
 * */
public enum QueueEnum {

    QUEUE_ORDER("miaosha.order.direct", "miaosha.order", "miaosha.key");

    /**
     * 交换机名称
     * */
    private String exchange;

    /**
     * 队列名称
     * */
    private String name;

    /**
     * 路由键
     * */
    private String routeKey;


    QueueEnum(String exchange, String name, String routeKey){
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
