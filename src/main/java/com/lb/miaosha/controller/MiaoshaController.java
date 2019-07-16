package com.lb.miaosha.controller;

import com.lb.miaosha.commont.api.CommonResult;
import com.lb.miaosha.component.CreateOrderSender;
import com.lb.miaosha.dto.Order;
import com.lb.miaosha.dto.Stock;
import com.lb.miaosha.service.OrderService;
import com.lb.miaosha.service.RedisService;
import com.lb.miaosha.service.StockService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 实现InitializingBean，把商品库存数量加载到Redis
 * */
@RestController
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CreateOrderSender createOrderSender;

    /**
     * 用于生成动态下单连接
     * */
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public CommonResult getPath(){
        String path = "1234";
        return CommonResult.success(path);
    }

    /**
     * 秒杀
     * */
    @RequestMapping(value = "/startMiaosha", method = RequestMethod.POST)
    public CommonResult product_list(@RequestParam("stockId") Long stockId){
        String stockKey = "stock_"+stockId;
        String value = redisService.get(stockKey);
        if(value == null){ //库存没有了
            return CommonResult.failed("找不到此商品");
        }
        Long stockNum = Long.parseLong(value);
        if(stockNum <= 0){
            return CommonResult.failed("秒杀失败，没有库存了");
        }
        Long userId = 111L;
        Order order = orderService.getOrderByUidAndSid(userId, stockId);
        if(order != null){
            return CommonResult.failed("秒杀成功，不能重复秒杀");
        }

        //购买消息入队列
        order = new Order();
        order.setUserId(userId);
        order.setStockId(stockId);
        createOrderSender.sendMessage(order);
        return CommonResult.success("排队当中");
    }

    /**
     * 客户端轮询是否秒杀成功
     * */
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public CommonResult getResult(@RequestParam("stockId") Long stockId){
        Long userId = 111L;
        Order order = orderService.getOrderByUidAndSid(userId, stockId);
        if(order != null){
            return CommonResult.success(order.getId(), "秒杀成功");
        }
        String stockKey = "stock_"+stockId;
        Long stockNum = Long.parseLong(redisService.get(stockKey));
        if(stockNum <= 0){
            return CommonResult.failed("卖完了");
        }
        return CommonResult.failed("排队中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Stock> stocks = stockService.getAllStock();
        for(Stock stock: stocks){
            String stockKey = "stock_"+stock.getId();
            redisService.set(stockKey, String.valueOf(stock.getStockNum()));
        }
    }

}
