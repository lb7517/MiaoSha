package com.lb.miaosha.service;

import com.lb.miaosha.dto.Order;

public interface OrderService {

    Order getOrderByOrderId(Long id);

    Order getOrderByUidAndSid(Long userId, Long stockId);

    boolean insert(Order order);
}
