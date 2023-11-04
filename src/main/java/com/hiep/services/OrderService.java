package com.hiep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiep.models.Order;
import com.hiep.services.preService.OrderS;

@Service
public class OrderService {
    @Autowired
    private OrderS orderS;

    public Order save(Order order) {
        return orderS.save(order);
    }
}
