package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {}
