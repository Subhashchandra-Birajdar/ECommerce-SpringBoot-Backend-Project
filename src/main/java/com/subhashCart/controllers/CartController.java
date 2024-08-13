package com.subhashCart.controllers;

import com.subhashCart.dtos.CartDTO;
import com.subhashCart.models.Cart;
import com.subhashCart.repositories.CartDao;
import com.subhashCart.repositories.CustomerDao;
import com.subhashCart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;


    @PostMapping(value = "/cart/add")
    public ResponseEntity<Cart> addProductToCartHander(@RequestBody CartDTO cartdto , @RequestHeader("token")String token){

        Cart cart = cartService.addProductToCart(cartdto, token);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    //
    @GetMapping(value = "/cart")
    public ResponseEntity<Cart> getCartProductHandler(@RequestHeader("token")String token){
        return new ResponseEntity<>(cartService.getCartProduct(token), HttpStatus.ACCEPTED);
    }


    @DeleteMapping(value = "/cart")
    public ResponseEntity<Cart> removeProductFromCartHander(@RequestBody CartDTO cartdto ,@RequestHeader("token")String token){

        Cart cart = cartService.removeProductFromCart(cartdto, token);
        return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }


    @DeleteMapping(value = "/cart/clear")
    public ResponseEntity<Cart> clearCartHandler(@RequestHeader("token") String token){
        return new ResponseEntity<>(cartService.clearCart(token), HttpStatus.ACCEPTED);
    }


}
