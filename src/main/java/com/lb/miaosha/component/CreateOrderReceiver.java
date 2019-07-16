package com.lb.miaosha.component;

import com.lb.miaosha.dto.Order;
import com.lb.miaosha.dto.QueueEnum;
import com.lb.miaosha.dto.Stock;
import com.lb.miaosha.service.OrderService;
import com.lb.miaosha.service.RedisService;
import com.lb.miaosha.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = "miaosha.order") //注意这里是写队列名
public class CreateOrderReceiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreateOrderReceiver.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @RabbitHandler
    public void handler(Order order){
        LOGGER.info("开始生成订单: ", order);

        Long userId = order.getUserId();
        Long stockId = order.getStockId();

        Stock stock = stockService.getStockBySid(stockId);
        if(stock.getStockNum() <= 0){ //没有库存了
            LOGGER.info("没有库存了");
            return;
        }
        Order orderTmp = orderService.getOrderByUidAndSid(userId, stockId);
        if(orderTmp != null){
            return;
        }else{
            //减库存，下单 写入秒杀订单
            orderTmp = new Order();
            orderTmp.setStockId(stockId);
            orderTmp.setUserId(userId);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderTmp.setCreateTime(new Date());
            orderService.insert(orderTmp);

            String stockKey = "stock_"+stockId;
            redisService.set(stockKey,  String.valueOf(stock.getStockNum() - 1));
            stockService.updateStock(stockId);
        }
    }
}
