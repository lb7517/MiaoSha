package com.lb.miaosha.service.impl;

import com.lb.miaosha.dao.StockMapper;
import com.lb.miaosha.dto.Stock;
import com.lb.miaosha.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Stock getStockBySid(Long stockId) {
        return stockMapper.getStockBySid(stockId);
    }

    @Override
    public boolean updateStock(Long stockId) {
        return stockMapper.update(stockId);
    }

    @Override
    public List<Stock> getAllStock() {
        return stockMapper.getListStock();
    }
}
