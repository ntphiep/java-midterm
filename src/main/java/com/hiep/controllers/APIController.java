package com.hiep.controllers;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.*;
import com.hiep.services.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
public class APIController {
    // Services list

    @Autowired  
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private BrandService brandService;


    // All api list

    @GetMapping("/api/product/get-all")
    public Iterable<Product> getAllProduct(){
        return productService.findAll();
    }

    @GetMapping("/api/brand/get-all")
    public Iterable<Brand> getAllBrand(){
        return brandService.findAll();
    }

    @GetMapping("/api/category/get-all")
    public Iterable<Category> getAllCategory(){
        return categoryService.findAll();
    }

    @PostMapping("/api/user/login/user-valid")
    public User getValidUser(@RequestBody Map<String,String> body){
        String username = body.get("username");
        String password = body.get("password");

        return userService.userLoginValid(username, password);
    }
    
    @PostMapping("/api/user/add")
    public String addUser(@RequestBody String request){
        Gson gson = new Gson();
        User user = gson.fromJson(request, User.class);

        if (userService.checkUserExist(user.getUsername())){
            return "Username is exist!";
        }

        String rePassword = user.getCustomer().getName();
        
        if (!rePassword.equals(user.getPassword())){
            return "Wrong password!";
        }

        user.getCustomer().setName(null);
        user.setCustomer(customerService.save(user.getCustomer()));
        userService.save(user);

        return "Sign up successfully!";
    }


    @PostMapping("/api/cart/add")
    public Cart addCart(@RequestBody Map<String, Long> body) {
        Long user_id = body.get("user_id");
        Long product_id = body.get("product_id");

        int quantity = Integer.parseInt(body.get("quantity").toString());

        if (quantity == 0) {
            if(cartService.checkProductExist(user_id, product_id)){
                cartService.updateQuantity(user_id, product_id);
                return cartService.findByUserIdAndProdId(user_id, product_id);
            }

            User user = userService.findById(user_id);
            Product product = productService.findByID(product_id);
            Cart cart = new Cart(product.getName(), product_id, product.getPrice(), product.getImage(), user);

            return cartService.save(cart);
        } else {
            if(cartService.checkProductExist(user_id, product_id)) {
                cartService.updateQuantity(user_id, product_id, quantity);
                return cartService.findByUserIdAndProdId(user_id, product_id);
            }
        }

        User user = userService.findById(user_id);
        Product product = productService.findByID(product_id);
        Cart cart = new Cart(product.getName(), product_id, product.getPrice(), product.getImage(), quantity, user);

        return cartService.save(cart);
    }

    @GetMapping("/api/cart/all-by-id/{id}")
    public Iterable<Cart> getCartByUserID(@PathVariable("id") Long user_id){
        return cartService.findAllByUserID(user_id);
    }

     
    @GetMapping("/api/product/find/{id}")
    public Product getDetails(@PathVariable("id") Long id) {
        return productService.findByID(id);
    }

    @GetMapping("/api/product/search/{id}")
    public Iterable<Product> getSearchAdvanced(@PathVariable("id")String text) {
        return productService.searchAdvance(text);
    }

    @GetMapping("/api/product/find-by-brand-id/{id}")
    public Iterable<Product> getAllProdByBrandId(@PathVariable("id")Long brand_id) {
        return productService.findAllProdByBrandId(brand_id);
    }

    @GetMapping("/api/product/find-by-range-price/{id}")
    public Iterable<Product> getAllProdByRangePrice(@PathVariable("id")Double price) {
        return productService.findAllByLessPrice(price);
    }



    @GetMapping("/api/cart/total/{id}")
    public Double totalCartByUserId(@PathVariable("id") Long user_id){
        return cartService.totalCartByUserId(user_id);
    }

    @DeleteMapping("/api/cart/delete/{id}")
    public boolean removeCartByID(@PathVariable("id") Long id){
        if (cartService.checkByID(id)) {
            cartService.deleteByID(id);
            return true;
        }

        return false;
    }

    @GetMapping("/api/customer/find/{id}")
    public Customer getByID(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @PostMapping("/api/order/add")
    public Order getAddOrder(@RequestBody Map<String,Long> body){
        Customer customer = customerService.findById(body.get("customer_id"));
        Double total = cartService.totalCartByUserId(body.get("user_id"));
        Iterable<Cart> carts = cartService.findAllByUserID(body.get("user_id"));
        Order order = new Order();

        order.setDate(LocalDateTime.now());
        order.setCustomer(customer);
        order.setTotal(total);
        order = orderService.save(order);

        for (Cart cart: carts){
            Product product = productService.findByID(cart.getProduct_id());
            OrderItem orderItem = new OrderItem();
            
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cart.getQuantity());
            orderItemService.save(orderItem);
            productService.updateQuantity(product.getId(),cart.getQuantity());
            cartService.deleteByID(cart.getId());
        }

        return orderService.save(order);
    }



    @PostMapping("/api/customer/update")
    public Customer postUpdateCus(@RequestBody Map<String,String> body){
        Long customer_id = Long.parseLong(body.get("id"));
        Customer customer = customerService.findById(customer_id);

        customer.setAddress(body.get("address"));
        customer.setName(body.get("name"));
        customer.setPhone(body.get("phone"));
        customer.setEmail(body.get("email"));

        return customerService.save(customer);
    }
}
