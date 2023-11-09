package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.Category;
import com.hiep.services.preService.CategoryS;


@Service
public class CategoryService {
    @Autowired
    private CategoryS categoryS;

    public Iterable<Category> findAll() {
        return categoryS.findAll();
    }
}
