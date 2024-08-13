package com.subhashCart.services;

import com.subhashCart.dtos.CartDTO;
import com.subhashCart.exceptions.CartItemNotFound;
import com.subhashCart.exceptions.ProductNotFoundException;
import com.subhashCart.models.Cart;

public interface CartService {

    public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound;
    public Cart getCartProduct(String token);
    public Cart removeProductFromCart(CartDTO cartDto,String token) throws ProductNotFoundException;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);

    public Cart clearCart(String token);

}
