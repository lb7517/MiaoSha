package com.lb.miaosha.dao;

import com.lb.miaosha.dto.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    Order getOrderByOrderId(Long id);

    Order getOrderByUidAndSid(@Param("userId") Long userId, @Param("stockId") Long stockId);

    boolean insert(Order order);

}
