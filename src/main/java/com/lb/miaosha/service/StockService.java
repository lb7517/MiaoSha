package com.lb.miaosha.service;

import com.lb.miaosha.dto.Stock;

import java.util.List;

public interface StockService {
    /**
     * 通过商品id差库存
     * */
    Stock getStockBySid(Long stockId);

    /**
     * 通过商品id差库存
     * */
    boolean updateStock(Long stockId);


    /**
     * 获取所有库存记录
     * */
    List<Stock> getAllStock();
}
