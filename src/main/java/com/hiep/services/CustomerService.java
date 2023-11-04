package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.Customer;
import com.hiep.services.preService.CustomerS;


@Service
public class CustomerService {
    @Autowired
    private CustomerS customerS;

    public Customer save(Customer customer) {
        return customerS.save(customer);
    }

    public Customer findById(Long id) {
        return customerS.findById(id).get();
    }
}