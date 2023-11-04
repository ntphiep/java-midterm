package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.OrderItem;
import com.hiep.services.preService.OrderItemS;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemS orderItemS;

    public void save(OrderItem orderItem) {
        orderItemS.save(orderItem);
    }
}
