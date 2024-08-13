package com.subhashCart.services;

import com.subhashCart.dtos.CartDTO;
import com.subhashCart.models.CartItem;

public interface CartItemService {

    public CartItem createItemforCart(CartDTO cartdto);

}
