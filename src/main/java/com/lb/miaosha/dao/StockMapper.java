package com.lb.miaosha.dao;

import com.lb.miaosha.dto.Stock;

import java.util.List;

public interface StockMapper {

    /**
     * 通过商品id差库存
     * */
    Stock getStockBySid(Long sid);

    boolean insert(Stock stock);

    boolean update(Long id);

    /**
     * 获取所有库存记录
     * */
    List<Stock> getListStock();
}
