package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.Product;
import com.hiep.services.preService.ProductS;


@Service
public class ProductService {
    @Autowired
    private ProductS productS;

    public Iterable<Product> findAll() {
        return productS.findAll();
    }

    public Iterable<Product> searchAdvance(String text){
        return productS.searchAdvance(text);
    }
    
    public void updateQuantity(Long id,int quan){
        productS.updateQuantity(id,quan);
    }

    public Product findByID(Long id) {
        if(productS.findById(id).isPresent()) {
            return productS.findById(id).get();
        } else {
            return null;
        }            
    }

    public Iterable<Product> findAllProdByBrandId(Long brandId) {
        return productS.findAllProByBrandId(brandId);
    }

    public Iterable<Product> findAllByLessPrice(Double price) {
        return productS.findAllByLessRange(price);

    }
}
