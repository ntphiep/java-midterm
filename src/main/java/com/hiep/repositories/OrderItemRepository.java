package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.OrderItem;


@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {}
