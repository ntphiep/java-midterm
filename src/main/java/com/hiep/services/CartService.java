package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.Cart;
import com.hiep.services.preService.CartS;


@Service
public class CartService {
    @Autowired
    private CartS cartS;

    public Cart save(Cart cart){
        return cartS.save(cart);
    }

    public Iterable<Cart> findAllByUserID(Long id) {
        return cartS.findAllByUserId(id);
    }
    public boolean checkProductExist(Long user_id, Long product_id) {
        return cartS.findProduct(user_id, product_id) != null;
    }

    public void deleteByID(Long id) {
        cartS.deleteById(id);
    }
    public boolean checkByID(Long id){
        return cartS.findById(id).isPresent();
    }

    public void updateQuantity(Long userId, Long productId) {
        cartS.updateQuantity(userId, productId);
    }
    public void updateQuantity(Long userId, Long productId, int quantity) {
        cartS.updateQuantity(userId, productId, quantity);
    }
    public Cart findByUserIdAndProdId(Long userId, Long productId) {
        return cartS.findByUserIdAndProductId(userId, productId);
    }

    public Double totalCartByUserId(Long userId) {
        return cartS.totalCartBy(userId);
    }
}
