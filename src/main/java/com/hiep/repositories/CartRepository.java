package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.Cart;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {}
