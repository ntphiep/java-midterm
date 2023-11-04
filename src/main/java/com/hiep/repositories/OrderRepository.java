package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {}
