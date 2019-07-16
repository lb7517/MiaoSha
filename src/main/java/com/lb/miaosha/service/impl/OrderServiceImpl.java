package com.lb.miaosha.service.impl;

import com.lb.miaosha.dao.OrderMapper;
import com.lb.miaosha.dto.Order;
import com.lb.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService  {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderByOrderId(Long id) {
        return orderMapper.getOrderByOrderId(id);
    }

    @Override
    public Order getOrderByUidAndSid(Long userId, Long stockId) {
        return orderMapper.getOrderByUidAndSid(userId, stockId);
    }

    @Override
    public boolean insert(Order order) {
        return orderMapper.insert(order);
    }
}
