package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.Brand;
import com.hiep.services.preService.BrandS;


@Service
public class BrandService {
    @Autowired
    private BrandS brandS;

    public Iterable<Brand> findAll() {
        return brandS.findAll();
    }
}
